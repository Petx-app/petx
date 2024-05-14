package com.petx.api.dto.Usuario;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TrocarSenhaDTO {

    private String codigo;

    private String email;

    private String senha;
}
