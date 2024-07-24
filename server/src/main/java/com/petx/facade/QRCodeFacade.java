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
        Usuario usuario = pet.getDono();

        QRCodeDTO QRCodeDTO = new QRCodeDTO(pet.getNome(), pet.getEspecie(), usuario.getNome(), usuario.getTelefone(), pet.getImage().getNomeImagem());
        return QRCodeDTO;
    }
}