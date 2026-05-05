package com.B2dev.usuario.business.dto;

import com.B2dev.usuario.infrastructure.entity.Endereco;
import com.B2dev.usuario.infrastructure.entity.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> ENDERECO;
    private List<TelefoneDTO> telefone;







}
