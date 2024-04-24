package com.petx.facade;

import com.petx.api.dto.PetDTO;
import com.petx.domain.Pet;
import com.petx.mapper.GetQRCodeResponseMapper;
import com.petx.mapper.PetMapper;
import com.petx.service.PetService;
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

    public void cadastrar(PetDTO petDTO) {
        Pet pet = mapper.toEntity(petDTO);
        service.cadastrar(pet);
    }

    public PetDTO buscarUUID(UUID uuid) {
        Pet pet = service.buscarUUID(uuid);
        return mapper.toDTO(pet);
    }

    public List<PetDTO> buscarTodos(Long id) {
        List<Pet> pets = service.buscarTodos(id);
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

    public void atualizar(PetDTO petDTO, UUID uuid) {
        Pet pet = mapper.toEntity(petDTO);
        service.atualizar(pet, uuid);
    }

    public void deletar(UUID uuid) {
        service.deletar(uuid);
    }
}