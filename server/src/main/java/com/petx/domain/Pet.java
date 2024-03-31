package com.petx.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="dono_id")
    private Usuario dono;

    private String nome;

    private String especie;

    private String raca;

    private String cor;

    private String porte;

    private String peso;

    private String sexo;

    private Boolean cadastrado;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "data_cadastro", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataCadastro;
}
