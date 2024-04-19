package com.petx.domain;

import jakarta.persistence.*;


import lombok.Data;

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
}