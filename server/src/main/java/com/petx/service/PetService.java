package com.petx.service;

import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import com.petx.repository.PetRepository;
import com.petx.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrar(Pet novoPet) {
        UUID petUUID = novoPet.getUuid();
        Long usuarioID = novoPet.getDono().getId();

        Optional<Pet> optionalPet = petRepository.findByUuid(petUUID);
        if (optionalPet.isPresent()) {
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

            Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioID);
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

    public List<Pet> buscarTodos(Long id) throws EntityNotFoundException {
        List<Pet> pets = petRepository.findByDonoId(id);
        if (!pets.isEmpty()) {
            return pets;
        }
        throw new EntityNotFoundException("Nao tem pet cadastrado");
    }

    public Pet buscarUUID(UUID uuid) throws EntityNotFoundException {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent()) {
            return optionalPet.get();
        }
        throw new EntityNotFoundException("pet nao encontrado");
    }

    public void deletar(UUID uuid) throws EntityNotFoundException {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent()) {
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

    public void atualizar(Pet atualizarPet, UUID uuid) throws EntityNotFoundException {
        Optional<Pet> optionalPet = petRepository.findByUuid(uuid);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setNome(atualizarPet.getNome());
            pet.setEspecie(atualizarPet.getEspecie());
            pet.setRaca(atualizarPet.getRaca());
            pet.setCor(atualizarPet.getCor());
            pet.setPorte(atualizarPet.getPorte());
            pet.setPeso(atualizarPet.getPeso());
            pet.setGenero(atualizarPet.getGenero());
            pet.setDataNascimento(atualizarPet.getDataNascimento());
            petRepository.save(pet);
        } else {
            throw new EntityNotFoundException("pet nao encontrado para ser deletado");
        }
    }
}