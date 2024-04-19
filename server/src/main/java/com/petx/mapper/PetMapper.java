package com.petx.mapper;

import com.petx.api.dto.PetDTO;
import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PetMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    public Pet toEntity(PetDTO dto) {
        Pet entity = new Pet();
        Usuario dono = null;

        if (dto.getDono() != null) {
            dono = usuarioMapper.toEntity(dto.getDono());
        }

        entity.setUuid(dto.getUuid());
        entity.setDono(dono);
        entity.setNome(dto.getNome());
        entity.setEspecie(dto.getEspecie());
        entity.setRaca(dto.getRaca());
        entity.setCor(dto.getCor());
        entity.setPorte(dto.getPorte());
        entity.setPeso(dto.getPeso());
        entity.setGenero(dto.getGenero());
        entity.setCadastrado(dto.getCadastrado());
        entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }

    public PetDTO toDTO(Pet pet) {
        if (pet == null) {
            return null;
        }
        PetDTO dto = new PetDTO();
        dto.setNome(pet.getNome());
        dto.setEspecie(pet.getEspecie());
        dto.setRaca(pet.getRaca());
        dto.setCor(pet.getCor());
        dto.setPorte(pet.getPorte());
        dto.setPeso(pet.getPeso());
        dto.setGenero(pet.getGenero());
        dto.setCadastrado(pet.getCadastrado());
        dto.setDataNascimento(pet.getDataNascimento());
        return dto;
    }
}