package com.petx.api.dto;

import com.petx.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PetDTO {

    private UUID uuid;

    private Usuario dono;

    private String nome;

    private String especie;

    private String raca;

    private String cor;

    private String porte;

    private String peso;

    private String sexo;

    private Boolean cadastrado;

    private Date dataNascimento;

    private Date dataCadastro;
}
