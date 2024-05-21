package com.petx.pet;

import com.petx.api.exceptions.PetNotCadastro;
import com.petx.domain.pet.Pet;
import com.petx.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class ConsultarQRCodePet {

    @Autowired
    private PetRepository petRepository;

    @Test
    public void petNaoExiste() {
        UUID uuidNaoExiste = UUID.fromString("39a8220e-f085-4f39-8cd1-727ee2a28e4d");
        //pet nao existe registro
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            Optional<Pet> optionalPet = petRepository.findByUuid(uuidNaoExiste);
            if (optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
            } else {
                throw new EntityNotFoundException("pet nao encontrado");
            }
        });
        Assertions.assertEquals("pet nao encontrado", thrown.getMessage());
    }


    @Test
    public void petExisteCadastrado() {
        UUID uuidCadastrado = UUID.fromString("ca337f89-c7d4-4101-a328-7d11442667c4");
        //pet existe e tem cadastro/vinculo com usuario
            Optional<Pet> optionalPet = petRepository.findByUuid(uuidCadastrado);
            if (optionalPet.isPresent()) {
                if(optionalPet.get().getCadastrado()){
                    Pet pet = optionalPet.get();
                    //retornar pet cadastrado
                }

            }
        Assertions.assertTrue(optionalPet.get().getCadastrado(), "pet cadastrado");
    }



    @Test
    public void petExisteSemCadastro() throws PetNotCadastro{
        UUID uuidNaoCadastrado = UUID.fromString("ca337f89-c7d4-4101-a328-7d11442667c1");
        //pet existe mas nao tem cadastro/vinculo com usuario
        PetNotCadastro thrown = Assertions.assertThrows(PetNotCadastro.class, () -> {
            Optional<Pet> optionalPet = petRepository.findByUuid(uuidNaoCadastrado);
            if (optionalPet.isPresent()) {
                if (optionalPet.get().getCadastrado()) {
                    Pet pet = optionalPet.get();
                }
                throw new PetNotCadastro("pet nao cadastrado");
            }
        });
        Assertions.assertEquals("pet nao cadastrado", thrown.getMessage());
    }
}