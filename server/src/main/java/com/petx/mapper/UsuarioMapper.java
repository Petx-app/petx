package com.petx.mapper;


import com.petx.api.dto.LoginUsuarioDTO;
import com.petx.api.dto.UsuarioDTO;
import com.petx.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntityLogin(LoginUsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        return entity;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail().toLowerCase());
        entity.setSenha(dto.getSenha());
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        return entity;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setNome(usuario.getNome());
        dto.setTelefone(usuario.getTelefone());
        return dto;
    }
}