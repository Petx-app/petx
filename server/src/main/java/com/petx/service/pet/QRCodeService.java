package com.petx.service.pet;

import com.petx.api.exceptions.PetNotCadastro;
import com.petx.domain.pet.Pet;
import com.petx.domain.pet.PetImagem;
import com.petx.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QRCodeService {

    @Autowired
    private PetRepository petRepository;

    @Value("${base.url.imagem.pets}")
    private String caminholinkImagem;

    public Pet buscarQRCode(UUID uuid) throws PetNotCadastro {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent()) {
            if (optionalPet.get().getCadastrado()) {
                Pet pet = optionalPet.get();
                PetImagem petImagem = pet.getImage();
                petImagem.setNomeImagem(caminholinkImagem + petImagem.getNomeImagem());
                pet.setImage(petImagem);
                return pet;
            }
            throw new PetNotCadastro("pet nao cadastrado");
        }
        throw new EntityNotFoundException("pet nao encontrado");
    }
}