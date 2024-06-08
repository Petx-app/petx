package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodigoValidacaoEmailDTO {

    @NotBlank
    @NotNull
    @Size(min = 4, max = 4)
    private String codigoVerificacao;

    @Email
    @NotNull
    @NotBlank
    private String email;
}
