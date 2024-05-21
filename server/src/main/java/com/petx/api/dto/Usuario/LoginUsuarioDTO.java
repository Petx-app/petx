package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUsuarioDTO {

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String senha;
}