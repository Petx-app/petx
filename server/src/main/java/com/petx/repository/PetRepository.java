package com.petx.repository;

import com.petx.domain.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    Optional<Pet> findByUuid(UUID uuid);

    List<Pet> findByDonoUuid(UUID uuid);

    List<Pet> findByQrcodeGerado(boolean qrcodeGerado);
}