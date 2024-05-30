package com.petx.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailValidar {

    @NotNull
    @NotBlank
    private String email;
}
