package com.petx.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Usuario {

    @Id
    private UUID uuid;

    private String email;

    private String senha;

    private String nome;

    private String telefone;

    private String cidade;

    private String estado;

    private String idGoogle;

    private Boolean cadastroFinalizado;

    private LocalDateTime dtInclusao;

    @PrePersist
    protected void onCreate() {
        this.dtInclusao = LocalDateTime.now();
        if (this.cadastroFinalizado == null) {
            this.cadastroFinalizado = false;
        }
    }
}