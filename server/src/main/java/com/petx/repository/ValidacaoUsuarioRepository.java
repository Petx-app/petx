package com.petx.repository;

import com.petx.domain.usuario.ValidacaoEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ValidacaoUsuarioRepository extends JpaRepository<ValidacaoEmail, Long> {

    Optional<ValidacaoEmail> findByEmail(String email);
}