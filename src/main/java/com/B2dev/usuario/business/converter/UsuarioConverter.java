package com.B2dev.usuario.business.converter;

import com.B2dev.usuario.business.dto.EnderecoDTO;
import com.B2dev.usuario.business.dto.TelefoneDTO;
import com.B2dev.usuario.business.dto.UsuarioDTO;
import com.B2dev.usuario.infrastructure.entity.Endereco;
import com.B2dev.usuario.infrastructure.entity.Telefone;
import com.B2dev.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) return null;

        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEndereco())) // Fique atento se no DTO é getEndereco ou getEnderecos
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        if (enderecoDTOS == null) {
            return new ArrayList<>(); // Evita o NullPointerException se o campo vier nulo
        }
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) return null;

        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS) {
        if (telefoneDTOS == null) {
            return new ArrayList<>(); // Proteção contra NullPointer
        }
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        if (telefoneDTO == null) return null;

        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        if (usuario == null) return null;

        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .endereco(paraListaEnderecoDTO(usuario.getEnderecos())) // Ajustado o getter da entidade (plural)
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))// Ajustado o getter da entidade (plural)
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {
        if (enderecos == null) {
            return new ArrayList<>();
        }
        return enderecos.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        if (endereco == null) return null;

        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones) {
        if (telefones == null) {
            return new ArrayList<>();
        }
        return telefones.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        if (telefone == null) return null;

        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Usuario updateUsuario (UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

public Endereco updateEndereco (EnderecoDTO dto, Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .build();

               }

               public Telefone updateTelefone (TelefoneDTO dto, Telefone entity ){
                 return Telefone.builder()
                .id(entity.getId())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .build();
               }

               public Endereco paraEnderecoEntity ( EnderecoDTO dto , Long idUsuario ){
                 return Endereco.builder()
                         .rua(dto.getRua())
                         .numero(dto.getNumero())
                         .complemento(dto.getComplemento())
                         .cidade(dto.getCidade())
                         .estado(dto.getEstado())
                         .cep(dto.getCep())
                         .usuario_id(idUsuario)
                         .build() ;

               }

               public Telefone paraTelefoneEntity ( TelefoneDTO dto, Long idUsuario){
             return Telefone.builder()
                .numero(dto.getNumero())
                .ddd(dto.getDdd())
                .usuario_id(idUsuario)
                .build();
               }








}
