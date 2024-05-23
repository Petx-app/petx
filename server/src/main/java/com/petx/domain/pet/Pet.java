package com.petx.domain.pet;

import com.petx.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "uuid_dono")
    private Usuario dono;

    private String nome;

    private String especie;

    private String raca;

    private String cor;

    private String porte;

    private String peso;

    private String genero;

    private Boolean cadastrado;

    private Date dataNascimento;

    private Date dataCadastro;

    private Date dataInclusao;

    @PrePersist
    protected void onCreate() {
        dataInclusao = new Date();
    }
}