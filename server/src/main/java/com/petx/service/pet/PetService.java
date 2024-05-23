package com.petx.service.pet;

import com.petx.domain.pet.Pet;
import com.petx.domain.usuario.Usuario;
import com.petx.repository.PetRepository;
import com.petx.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrar(Pet novoPet, UUID uuidDono) {
        UUID uuid = novoPet.getUuid();

        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent() && !optionalPet.get().getCadastrado()) {
            Pet pet = optionalPet.get();
            pet.setNome(novoPet.getNome());
            pet.setEspecie(novoPet.getEspecie());
            pet.setRaca(novoPet.getRaca());
            pet.setCor(novoPet.getCor());
            pet.setPorte(novoPet.getPorte());
            pet.setPeso(novoPet.getPeso());
            pet.setGenero(novoPet.getGenero());
            pet.setCadastrado(true);
            pet.setDataNascimento(novoPet.getDataNascimento());
            pet.setDataCadastro(novoPet.getDataCadastro());

            Optional<Usuario> optionalUsuario = usuarioRepository.findByUuid(uuidDono);
            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();
                pet.setDono(usuario);
            } else {
                throw new RuntimeException("Usuario com id nao encontrado");
            }
            petRepository.save(pet);
        } else {
            throw new RuntimeException("Nao foi possivel salvar pet");
        }
    }

    public List<Pet> buscarTodos(UUID uuidDono) throws EntityNotFoundException {
        List<Pet> pets = petRepository.findByDonoUuid(uuidDono);
        if (!pets.isEmpty()) {
            return pets;
        }
        throw new EntityNotFoundException("Nao tem pet cadastrado");
    }

    public Pet buscarUUID(UUID uuid, UUID uuidDono) throws EntityNotFoundException {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent() && optionalPet.get().getCadastrado() && (Objects.equals(optionalPet.get().getDono().getUuid(), uuidDono))) {
            return optionalPet.get();
        }
        throw new EntityNotFoundException("pet nao encontrado");
    }

    public void deletar(UUID uuid, UUID uuidDono) throws EntityNotFoundException {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent() && optionalPet.get().getCadastrado() && (Objects.equals(optionalPet.get().getDono().getUuid(), uuidDono))) {
            Pet pet = optionalPet.get();
            pet.setDono(null);
            pet.setNome(null);
            pet.setEspecie(null);
            pet.setRaca(null);
            pet.setCor(null);
            pet.setPorte(null);
            pet.setPeso(null);
            pet.setGenero(null);
            pet.setCadastrado(false);
            pet.setDataNascimento(null);
            petRepository.save(pet);
        } else {
            throw new EntityNotFoundException("Pet nao encontrado para delete");
        }
    }

    public void atualizar(Pet atualizarPet, UUID uuidDono) throws EntityNotFoundException {
        UUID uuid = atualizarPet.getUuid();

        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent() && optionalPet.get().getCadastrado() && (Objects.equals(optionalPet.get().getDono().getUuid(), uuidDono))) {
            Pet pet = optionalPet.get();
            pet.setNome(atualizarPet.getNome());
            pet.setEspecie(atualizarPet.getEspecie());
            pet.setRaca(atualizarPet.getRaca());
            pet.setCor(atualizarPet.getCor());
            pet.setPorte(atualizarPet.getPorte());
            pet.setPeso(atualizarPet.getPeso());
            pet.setGenero(atualizarPet.getGenero());
            pet.setDataNascimento(atualizarPet.getDataNascimento());
            pet.setDataCadastro(atualizarPet.getDataCadastro());
            petRepository.save(pet);
        } else {
            throw new EntityNotFoundException("pet nao encontrado para ser Atualizado");
        }
    }
}