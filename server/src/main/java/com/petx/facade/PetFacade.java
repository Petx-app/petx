package com.petx.facade;

import com.petx.api.dto.pet.ImagemPetDTO;
import com.petx.api.dto.pet.ListPetsDTO;
import com.petx.api.dto.pet.PetDTO;
import com.petx.domain.pet.Pet;
import com.petx.domain.pet.PetImagem;
import com.petx.mapper.pet.QRCodeMapper;
import com.petx.mapper.pet.PetMapper;
import com.petx.service.pet.PetService;
import com.petx.service.security.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    public void cadastrar(PetDTO petDTO, String token) throws IOException {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = mapper.toEntity(petDTO);
        service.cadastrar(pet, uuidDono);
    }

    public PetDTO buscarUUID(UUID uuid, String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = service.buscarUUID(uuid, uuidDono);
        return mapper.toDTO(pet);
    }

    public List<ListPetsDTO> buscarTodos(String token) {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        List<Pet> pets = service.buscarTodos(uuidDono);
        List<ListPetsDTO> listPetsDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            ListPetsDTO PetsDTOs = new ListPetsDTO();
            PetsDTOs.setUuid(pet.getUuid());
            PetsDTOs.setNome(pet.getNome());
            PetsDTOs.setRaca(pet.getRaca());
            if(pet.getImage() != null) {
                PetsDTOs.setImagem(pet.getImage().getNomeImagem());
            }
            listPetsDTOs.add(PetsDTOs);
        }
        return listPetsDTOs;
    }

    public void atualizar(PetDTO petDTO, String token) throws IOException {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        Pet pet = mapper.toEntity(petDTO);
        service.atualizar(pet, uuidDono);
    }

    public void deletar(UUID uuid, String token) throws IOException {
        UUID uuidDono = buscarIdToken.getIdDoUsuarioDoTokenJWT(token);
        service.deletar(uuid, uuidDono);
    }

    public void atualizarImagemPet(ImagemPetDTO imagemDTO) throws IOException {
        PetImagem imagem = mapper.toEntityPetImage(imagemDTO);
        service.atualizarImagemPet(imagem);
    }

    public void cadastrarImagemPet(ImagemPetDTO imagemDTO) throws IOException {
        PetImagem imagem = mapper.toEntityPetImage(imagemDTO);
        service.cadastrarImagemPet(imagem);
    }
}