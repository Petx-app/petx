package com.petx.facade;

import com.petx.api.dto.pet.QRCodeDTO;
import com.petx.api.exceptions.PetNotCadastro;
import com.petx.domain.pet.Pet;
import com.petx.domain.usuario.Usuario;
import com.petx.service.pet.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class QRCodeFacade {

    @Autowired
    private QRCodeService service;

    public QRCodeDTO buscarQRCode(UUID uuid) throws PetNotCadastro {
        Pet pet = service.buscarQRCode(uuid);
        QRCodeDTO QRCodeDTO = new QRCodeDTO();
        QRCodeDTO.setNomePet(pet.getNome());

        Usuario usuario = pet.getDono();
        QRCodeDTO.setTelefoneDono(usuario.getTelefone());
        QRCodeDTO.setNomeDono(usuario.getNome());
        return QRCodeDTO;
    }
}