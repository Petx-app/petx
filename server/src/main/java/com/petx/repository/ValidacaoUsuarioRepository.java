package com.petx.repository;

import com.petx.domain.usuario.ValidacaoEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ValidacaoUsuarioRepository extends JpaRepository<ValidacaoEmail, Long> {

    Optional<ValidacaoEmail> findByEmail(String email);
}