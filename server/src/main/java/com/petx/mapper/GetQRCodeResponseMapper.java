package com.petx.mapper;

import com.petx.api.dto.GetQRCodeResponseDTO;
import com.petx.domain.GetQRCodeResponse;
import org.springframework.stereotype.Component;

@Component
public class GetQRCodeResponseMapper {

    public GetQRCodeResponse toEntity(GetQRCodeResponseDTO dto) {
        GetQRCodeResponse entity = new GetQRCodeResponse();
        entity.setNomePet(dto.getNomePet());
        entity.setNomeDono(dto.getNomeDono());
        entity.setTelefoneDono(dto.getTelefoneDono());
        return entity;
    }

    public GetQRCodeResponseDTO toDTO(GetQRCodeResponse getQRCodeResponse) {
        if (getQRCodeResponse == null) {
            return null;
        }
        GetQRCodeResponseDTO dto = new GetQRCodeResponseDTO();
        dto.setNomePet(getQRCodeResponse.getNomePet());
        dto.setNomeDono(getQRCodeResponse.getNomeDono());
        dto.setTelefoneDono(getQRCodeResponse.getTelefoneDono());
        return dto;
    }
}