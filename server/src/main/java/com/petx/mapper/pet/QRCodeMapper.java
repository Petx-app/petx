package com.petx.mapper.pet;

import com.petx.api.dto.pet.GetQRCodeResponseDTO;
import com.petx.domain.pet.QRCode;
import org.springframework.stereotype.Component;

@Component
public class QRCodeMapper {

    public QRCode toEntity(GetQRCodeResponseDTO dto) {
        QRCode entity = new QRCode();
        entity.setNomePet(dto.getNomePet());
        entity.setNomeDono(dto.getNomeDono());
        entity.setTelefoneDono(dto.getTelefoneDono());

        return entity;
    }

    public GetQRCodeResponseDTO toDTO(QRCode QRCode) {
        if (QRCode == null) {
            return null;
        }
        GetQRCodeResponseDTO dto = new GetQRCodeResponseDTO();
        dto.setNomePet(QRCode.getNomePet());
        dto.setNomeDono(QRCode.getNomeDono());
        dto.setTelefoneDono(QRCode.getTelefoneDono());

        return dto;
    }
}