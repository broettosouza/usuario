package com.B2dev.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@Builder


public class Usuario  implements UserDetails {


    private Long id;

    private String nome;

    private String email;

    private String senha ;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> enderecos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private  List<Telefone> telefone;



    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    public @Nullable String getPassword() {
        return senha;
    }


    public String getUsername() {
        return email;
    }

    public List<Endereco> getEndereco() {
        return enderecos;
    }
}
