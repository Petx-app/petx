package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class UsuarioDTO {

    private UUID uuid;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    private String senha;

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