package com.petx.mapper;

import com.petx.api.dto.AdminDTO;
import com.petx.domain.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setUsuario(dto.getUsuario());
        admin.setSenha(dto.getSenha());
        return admin;
    }
}