package com.petx.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class ValidacaoEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String codigo;

    private boolean validado;

    private LocalDateTime horaInserida;

    @PrePersist
    protected void onCreate() {
        this.horaInserida = LocalDateTime.now();
        this.validado = false;
    }
}