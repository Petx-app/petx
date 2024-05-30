package com.petx.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ValidacaoSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @Column(unique = true)
    private UUID codigoValidar;

    private LocalDateTime horaInserida;

    @PrePersist
    protected void onCreate(){
        this.horaInserida = LocalDateTime.now();
        this.codigoValidar = UUID.randomUUID();
    }
}
