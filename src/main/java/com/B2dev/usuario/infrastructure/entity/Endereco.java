package com.B2dev.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
@Builder

public class Endereco {

    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;






}
