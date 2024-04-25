package com.petx.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotNull
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private String nome;

    @Size(min = 10, max = 10)
    private String telefone;
}