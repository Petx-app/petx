package com.petx.pet;

import com.petx.domain.pet.Pet;
import com.petx.domain.usuario.Usuario;
import com.petx.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class CadastrarPet {

    @Autowired
    private PetRepository petRepository;

    @Test
    @Transactional
    @Rollback
    public void cadastrarPet() {
        UUID uuidDoPet = UUID.fromString("ca337f89-c7d4-4101-a328-7d11442667c0");
        Usuario dono = new Usuario();
        dono.setId(10L);
        dono.setNome("joao");
        dono.setEmail("joao@gmail.com");
        dono.setSenha("123456");
        dono.setTelefone("11 912345678");

        //validando que esse pet nao tem cadastro algum
        Optional<Pet> optionalPet = petRepository.findByUuid(uuidDoPet);
        if(optionalPet.isPresent()) {
            Pet petVerificar = optionalPet.get();
            Assertions.assertFalse(petVerificar.getCadastrado(), "pet nao tem cadastro");
        }

        if(optionalPet.isPresent() && !optionalPet.get().getCadastrado()) {
            Pet petNovo = optionalPet.get();
            petNovo.setNome("Bob");
            petNovo.setEspecie("Cachorro");
            petNovo.setRaca("Labrador");
            petNovo.setCor("Marrom");
            petNovo.setPorte("Grande");
            petNovo.setPeso("25 kg");
            petNovo.setGenero("Macho");
            petNovo.setCadastrado(true);
            petNovo.setDataNascimento(new Date());
            petNovo.setDataCadastro(new Date());

            Pet petSalvo = petRepository.save(petNovo);

            Assertions.assertTrue(petSalvo.getCadastrado(), "pet cadastrado");
        }
    }

    @Test
    public void cadastrarPetUuidInexistente() {
        UUID uuidDoPetNaoExistente = UUID.fromString("ca337f89-c7d4-4101-a321-7d11442667c0");

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Optional<Pet> optionalPet = petRepository.findByUuid(uuidDoPetNaoExistente);
            if(optionalPet.isPresent()) {
                //cadastra o pet
            } else {
                throw new RuntimeException("Nao foi possivel salvar pet");
            }
        });
        Assertions.assertEquals("Nao foi possivel salvar pet", thrown.getMessage());
    }
}
