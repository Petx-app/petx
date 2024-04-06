package com.petx.repository;

import com.petx.api.dto.PetDTO;
import com.petx.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {
 
    Pet findByUuid(UUID uuid);

}