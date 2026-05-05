package com.B2dev.usuario.business;

import com.B2dev.usuario.business.converter.UsuarioConverter;
import com.B2dev.usuario.business.dto.UsuarioDTO;
import com.B2dev.usuario.infrastructure.entity.Usuario;
import com.B2dev.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO){
    Usuario usuario= usuarioConverter.paraUsuario(usuarioDTO);
    return usuarioConverter.paraUsuarioDTO(
            usuarioRepository.save(usuario)
    );
}
}
