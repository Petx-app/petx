package com.petx.api.dto.pet;

import com.petx.api.dto.Usuario.UsuarioDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Data
@Component
public class PetDTO {

    private UUID uuid;

    private UsuarioDTO dono;

    @NotBlank
    private String nome;

    private String especie;

    private String raca;

    private String cor;

    private String porte;

    private String peso;

    private String genero;

    private Boolean cadastrado;

    private Date dataNascimento;

    private Date dataCadastro;
}