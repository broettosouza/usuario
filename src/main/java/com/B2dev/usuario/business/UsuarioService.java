package com.B2dev.usuario.business;

import com.B2dev.usuario.business.converter.UsuarioConverter;
import com.B2dev.usuario.business.dto.UsuarioDTO;
import com.B2dev.usuario.infrastructure.entity.Usuario;
import com.B2dev.usuario.infrastructure.exceptions.ConflictException;
import com.B2dev.usuario.infrastructure.repository.UsuarioRepository;
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



}
