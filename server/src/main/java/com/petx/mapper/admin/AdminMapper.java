package com.petx.mapper.admin;

import com.petx.api.dto.admin.AdminDTO;
import com.petx.domain.admin.Admin;
import com.petx.domain.admin.UuidQRCodeGerado;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AdminMapper {

    public Admin toEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setUsuario(dto.getUsuario());
        admin.setSenha(dto.getSenha());
        return admin;
    }

    public UuidQRCodeGerado toEntityUuid(UUID dto){
        UuidQRCodeGerado uuid = new UuidQRCodeGerado();
        uuid.setUuidQRCode(dto);

        return uuid;
    }
}