package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String codigoVerificacao;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private String nome;

    @Size(min = 10, max = 10)
    private String telefone;
}