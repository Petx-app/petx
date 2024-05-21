package com.petx.repository;

import com.petx.domain.usuario.ValidacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ValidacaoSenhaRepository extends JpaRepository<ValidacaoSenha, Long> {

    Optional<ValidacaoSenha> findByCodigoValidar(UUID codigoValidacao);
}