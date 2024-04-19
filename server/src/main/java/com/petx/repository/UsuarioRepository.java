package com.petx.repository;

import com.petx.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findById(long id);

    Optional<Usuario> findByEmail(String email);
}