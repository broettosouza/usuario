package com.B2dev.usuario.business;

import com.B2dev.usuario.business.converter.UsuarioConverter;
import com.B2dev.usuario.business.dto.UsuarioDTO;
import com.B2dev.usuario.infrastructure.entity.Usuario;
import com.B2dev.usuario.infrastructure.exceptions.ConflictException;
import com.B2dev.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.B2dev.usuario.infrastructure.repository.UsuarioRepository;
import com.B2dev.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO){
    emailExiste(usuarioDTO.getEmail());
    usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
    Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
    usuario = usuarioRepository.save(usuario);
    return usuarioConverter.paraUsuarioDTO(usuario);
}





    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email ja cadastrado " + email);
            }

        }catch (ConflictException e ){
            throw new ConflictException ("Email ja cadastrado " , e.getCause ());

        }
    }


    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);

    }
    public Usuario buscarUsuarioPoremail(String email ){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }
    public void deleteUsuaioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
    public UsuarioDTO atualizacaoDadosUsuario(String token, UsuarioDTO dto){

    // Aqui buscamos o email do ususario atraves do token (tirar a obrigatoridade do email)
    String email = jwtUtil.extrairEmailtoken(token.substring(7));

     //criptografia de senha
    dto.setSenha(dto.getSenha()  != null ? passwordEncoder.encode(dto.getEmail()) : null );

    //Busca os dados do ususario no banco de dados
    Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
            new ResourceNotFoundException("Email não localizado "));

    //Mesclou os dados que recebemos na requisição DTO com os dado do banco de dado
     Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

    //Salvou os dado do usuario convertido e depoi pedou o retorno e converteu para UsuarioDTO
    return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}
