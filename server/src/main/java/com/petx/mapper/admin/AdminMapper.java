package com.petx.mapper.admin;

import com.petx.api.dto.admin.AdminDTO;
import com.petx.domain.admin.Admin;
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