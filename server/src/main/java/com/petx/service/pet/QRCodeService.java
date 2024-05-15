package com.petx.service.pet;

import com.petx.api.exceptions.PetNotCadastro;
import com.petx.domain.pet.Pet;
import com.petx.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QRCodeService {

    @Autowired
    private PetRepository petRepository;

    public Pet buscarQRCode(UUID uuid) throws PetNotCadastro {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent()) {
            if (optionalPet.get().getCadastrado()) {
                return optionalPet.get();
            }
            throw new PetNotCadastro("pet nao cadastrado");
        }
        throw new EntityNotFoundException("pet nao encontrado");
    }
}