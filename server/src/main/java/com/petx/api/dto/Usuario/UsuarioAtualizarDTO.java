package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data

public class UsuarioAtualizarDTO {

    private UUID uuid;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String telefone;

    @NotNull
    @NotBlank
    private String cidade;

    @NotNull
    @NotBlank
    private String estado;
}
