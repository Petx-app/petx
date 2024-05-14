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
    @JoinColumn(name = "id_dono")
    private Usuario dono;

    private String nome;

    private String especie;

    private String raca;

    private String cor;

    private String porte;

    private String peso;

    private String genero;

    private Boolean cadastrado;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "data_cadastro", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataCadastro;
}