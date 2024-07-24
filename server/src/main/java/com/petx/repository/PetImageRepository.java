package com.petx.repository;

import com.petx.domain.pet.PetImagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PetImageRepository extends JpaRepository<PetImagem, Long> {

    Optional<PetImagem> findById(Long id);
}
