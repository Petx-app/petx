package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailDTO {

    @NotBlank
    @NotNull
    @Email(message = "email invalido")
    private String email;
}