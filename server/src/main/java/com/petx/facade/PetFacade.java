package com.petx.facade;

import com.petx.api.dto.pet.PetDTO;
import com.petx.domain.pet.Pet;
import com.petx.mapper.pet.QRCodeMapper;
import com.petx.mapper.pet.PetMapper;
import com.petx.service.pet.PetService;
import com.petx.service.security.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PetFacade {

    @Autowired
    private PetService service;

    @Autowired
    private PetMapper mapper;

    @Autowired
    private QRCodeMapper getQRCodeRespoMapper;

    @Autowired
    UserTokenService buscarIdToken;

    public void cadastrar(PetDTO petDTO, String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = mapper.toEntity(petDTO);
        service.cadastrar(pet, uuidDono);
    }

    public PetDTO buscarUUID(UUID uuid, String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = service.buscarUUID(uuid, uuidDono);
        return mapper.toDTO(pet);
    }

    public List<PetDTO> buscarTodos(String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        List<Pet> pets = service.buscarTodos(uuidDono);
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            PetDTO petDTO = new PetDTO();
            petDTO.setUuid(pet.getUuid());
            petDTO.setNome(pet.getNome());
            petDTO.setEspecie(pet.getEspecie());
            petDTO.setRaca(pet.getRaca());
            petDTO.setCor(pet.getCor());
            petDTO.setPorte(pet.getPorte());
            petDTO.setPeso(pet.getPeso());
            petDTO.setGenero(pet.getGenero());
            petDTO.setDataNascimento(pet.getDataNascimento());
            petDTOs.add(petDTO);
        }
        return petDTOs;
    }

    public void atualizar(PetDTO petDTO, String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = mapper.toEntity(petDTO);
        service.atualizar(pet, uuidDono);
    }

    public void deletar(UUID uuid, String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        service.deletar(uuid, uuidDono);
    }
}