package com.petx.facade;

import com.petx.api.dto.PetDTO;
import com.petx.domain.Pet;
import com.petx.mapper.GetQRCodeResponseMapper;
import com.petx.mapper.PetMapper;
import com.petx.service.PetService;
import com.petx.service.UserTokenService;
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
    private GetQRCodeResponseMapper getQRCodeRespoMapper;

    @Autowired
    UserTokenService buscarIdToken;

    public void cadastrar(PetDTO petDTO, String token) {
        Long idDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = mapper.toEntity(petDTO);
        service.cadastrar(pet, idDono);
    }

    public PetDTO buscarUUID(UUID uuid, String token) {
        Long idDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = service.buscarUUID(uuid, idDono);
        return mapper.toDTO(pet);
    }

    public List<PetDTO> buscarTodos(String token) {
        Long idDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        List<Pet> pets = service.buscarTodos(idDono);
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
        Long idDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = mapper.toEntity(petDTO);
        service.atualizar(pet, idDono);
    }

    public void deletar(UUID uuid, String token) {
        Long idDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        service.deletar(uuid, idDono);
    }
}