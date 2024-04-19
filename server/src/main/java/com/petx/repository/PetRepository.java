package com.petx.repository;

import com.petx.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    Optional<Pet> findByUuid(UUID uuid);

    List<Pet> findByDonoId(Long id);

    List<Pet> findByCadastrado(boolean cadastrado);
}