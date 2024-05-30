package com.petx.api.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminDTO {

    private Long id;

    @NotBlank
    @NotNull
    private String usuario;

    @NotBlank
    @NotNull
    private String senha;
}