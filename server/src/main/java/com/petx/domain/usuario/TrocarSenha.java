package com.petx.domain.usuario;

import lombok.Data;

@Data
public class TrocarSenha {

    private String codigo;

    private String email;

    private String senha;
}
