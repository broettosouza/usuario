package com.B2dev.usuario.business.converter;


import com.B2dev.usuario.business.dto.EnderecoDTO;
import com.B2dev.usuario.business.dto.TelefoneDTO;
import com.B2dev.usuario.business.dto.UsuarioDTO;
import com.B2dev.usuario.infrastructure.entity.Endereco;
import com.B2dev.usuario.infrastructure.entity.Telefone;
import com.B2dev.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {


   public Usuario paraUsuario(UsuarioDTO usuarioDTO){
       return Usuario.builder()
               .nome(usuarioDTO.getNome())
               .email(usuarioDTO.getEmail())
               .senha(usuarioDTO.getSenha())
               .enderecos(paraListaEndereco(usuarioDTO.getEndereco()))
               .telefone(paraListaTelefone(usuarioDTO.getTelefones()))
               .build();


   }

public List<Endereco> paraListaEndereco (List<EnderecoDTO> enderecoDTOS){
return enderecoDTOS.stream().map(this::paraEndereco).toList();
}
public Endereco paraEndereco (EnderecoDTO enderecoDTO){
       return Endereco.builder()
               .rua(enderecoDTO.getRua())
               .numero(enderecoDTO.getNumero())
               .complemento(enderecoDTO.getComplemento())
               .cidade(enderecoDTO.getCidade())
               .estado(enderecoDTO.getEstado())
               .cep(enderecoDTO.getCep())
               .build();
          }

          public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
             return telefoneDTOS.stream().map(this::paraTelefone).toList();
          }

          public  Telefone paraTelefone (TelefoneDTO telefoneDTO){
       return Telefone.builder()
               .numero(telefoneDTO.getNumero())
               .ddd(telefoneDTO.getDdd())
               .build();
          }

    public UsuarioDTO paraUsuarioDTO (Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .endereco(paraListaEnderecoDTO(usuarioDTO.getEndereco()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();


    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();
    }
    public EnderecoDTO paraEnderecoDTO (Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public  TelefoneDTO paraTelefoneDTO (Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }
}

