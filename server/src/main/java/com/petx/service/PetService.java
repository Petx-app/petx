package com.petx.service;

import com.petx.api.exceptions.PetNotCadastro;
import com.petx.api.exceptions.PetNotFound;
import com.petx.domain.GetQRCodeResponse;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public void postPet(Pet pet) {
        petRepository.save(pet);
    }

    public GetQRCodeResponse getQRCode(UUID uuid) throws PetNotFound, PetNotCadastro {
        Pet pet = petRepository.findByUuid(uuid);

        if(pet == null){
            throw new PetNotFound("error: pet_not_found");
        }

        if(!pet.getCadastrado()){
            throw new PetNotCadastro("error: pet_not_cadastro");
        }

        GetQRCodeResponse getQRCodeResponse = new GetQRCodeResponse();
        getQRCodeResponse.setNomePet(pet.getNome());

        Usuario usuario = pet.getDono();
        getQRCodeResponse.setNomeDono(usuario.getNome());
        getQRCodeResponse.setTelefoneDono(usuario.getTelefone());

        return getQRCodeResponse;
        }

    }