package com.petx.service;

import com.petx.domain.GetQRCodeResponse;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServicePet {

    @Autowired
    private PetRepository petRepository;

    public void postPet(Pet pet) {
        petRepository.save(pet);
    }

    public GetQRCodeResponse getQRCode(UUID id) {
        Pet pet = petRepository.findByUuid(id);
        Usuario usuario = pet.getDono();

        GetQRCodeResponse getQRCodeResponse = new GetQRCodeResponse();
        getQRCodeResponse.setNomePet(pet.getNome());
        getQRCodeResponse.setNomeDono(usuario.getNome());
        getQRCodeResponse.setTelefoneDono(usuario.getTelefone());

        return getQRCodeResponse;
    }
}