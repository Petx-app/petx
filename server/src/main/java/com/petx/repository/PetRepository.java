package com.petx.repository;

import com.petx.domain.Pet;
import com.petx.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {
    Usuario findByUsuario(Usuario dono);

    Pet findByUUID(UUID uuid);
}
