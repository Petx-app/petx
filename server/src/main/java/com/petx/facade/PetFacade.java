package com.petx.facade;

import com.petx.api.dto.GetQRCodeResponseDTO;
import com.petx.api.exceptions.PetNotCadastro;
import com.petx.api.exceptions.PetNotFound;
import com.petx.domain.GetQRCodeResponse;
import com.petx.mapper.GetQRCodeResponseMapper;
import com.petx.mapper.PetMapper;
import com.petx.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class PetFacade {

    @Autowired
    private PetService petService;

    @Autowired
    private GetQRCodeResponseMapper getQRCodeResponseMapper;

    public GetQRCodeResponseDTO getQRCode(UUID uuid) throws PetNotCadastro, PetNotFound {

        GetQRCodeResponse entity = petService.getQRCode(uuid);

        GetQRCodeResponseDTO dto = getQRCodeResponseMapper.toDTO(entity);

        return dto;
    }

}
