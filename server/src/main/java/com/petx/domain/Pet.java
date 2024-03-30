package com.petx.domain;

import lombok.*;
import java.util.Date;

@Data
public class Pet {

    private String nome;

    private String especie;

    private String raca;

    private String cor;

    private String porte;

    private Date dataNascimento;

    private String peso;

}
