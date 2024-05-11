package com.petx.api.dto;

import lombok.Data;

@Data
public class UsuarioLogadoDTO {

    private Long id;
    private String nome;
    private String token;
}
