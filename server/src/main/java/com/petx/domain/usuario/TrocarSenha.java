package com.petx.domain.usuario;

import lombok.Data;
import java.util.UUID;

@Data
public class TrocarSenha {

    private UUID codigoValidacao;

    private String senha;
}