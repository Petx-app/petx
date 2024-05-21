package com.petx.mapper.pet;

import com.petx.api.dto.pet.QRCodeDTO;
import com.petx.domain.pet.QRCode;
import org.springframework.stereotype.Component;

@Component
public class QRCodeMapper {

    public QRCode toEntity(QRCodeDTO dto) {
        QRCode entity = new QRCode();
        entity.setNomePet(dto.getNomePet());
        entity.setNomeDono(dto.getNomeDono());
        entity.setTelefoneDono(dto.getTelefoneDono());

        return entity;
    }

    public QRCodeDTO toDTO(QRCode QRCode) {
        if (QRCode == null) {
            return null;
        }
        QRCodeDTO dto = new QRCodeDTO();
        dto.setNomePet(QRCode.getNomePet());
        dto.setNomeDono(QRCode.getNomeDono());
        dto.setTelefoneDono(QRCode.getTelefoneDono());

        return dto;
    }
}