package com.B2dev.usuario.controller;

import com.B2dev.usuario.business.UsuarioService;
import com.B2dev.usuario.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity <UsuarioDTO>  salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok( usuarioService.salvaUsuario(usuarioDTO));

    }
}
