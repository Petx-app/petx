package com.petx.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String telefone;

    private LocalDateTime dtInclusao;

    @PrePersist
    protected void onCreate() {
        this.dtInclusao = LocalDateTime.now();
    }
}