package com.B2dev.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefone")
@Builder


public class Telefone {

    private Long id;

    private String numero ;

    private String ddd;




}
