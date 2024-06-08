package com.petx.domain.usuario;

import lombok.Data;

@Data
public class CodigoValidacaoEmail {

    private String codigoVerificacao;

    private String email;
}