package com.petx.domain;

import lombok.Data;

@Data
public class Usuario {

    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String telefone;

}