package com.petx.api.dto.Usuario;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class UsuarioDTO {

    private UUID uuid;

    @NotBlank
    @NotNull
    @Size(min = 4, max = 4)
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