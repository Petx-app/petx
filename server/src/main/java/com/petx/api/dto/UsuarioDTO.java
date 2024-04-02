package com.petx.api.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String email;

    private String senha;

    private String nome;

    private String telefone;
}