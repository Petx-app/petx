package com.petx.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsuarioDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
