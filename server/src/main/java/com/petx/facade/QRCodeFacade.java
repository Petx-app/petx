package com.petx.facade;

import com.petx.api.dto.GetQRCodeResponseDTO;
import com.petx.api.exceptions.PetNotCadastro;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QRCodeFacade {

    @Autowired
    private QRCodeService service;

    public GetQRCodeResponseDTO buscarQRCode(UUID uuid) throws PetNotCadastro {
        Pet pet = service.buscarQRCode(uuid);
        GetQRCodeResponseDTO getQRCodeResponseDTO = new GetQRCodeResponseDTO();
        getQRCodeResponseDTO.setNomePet(pet.getNome());

        Usuario usuario = pet.getDono();
        getQRCodeResponseDTO.setTelefoneDono(usuario.getTelefone());
        getQRCodeResponseDTO.setNomeDono(usuario.getNome());
        return getQRCodeResponseDTO;
    }
}