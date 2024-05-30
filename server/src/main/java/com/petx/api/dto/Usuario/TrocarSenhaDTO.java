package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TrocarSenhaDTO {

    @NotNull
    @NotBlank
    private String senha;
}