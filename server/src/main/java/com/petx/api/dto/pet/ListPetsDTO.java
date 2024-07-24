package com.petx.api.dto.pet;

import lombok.Data;

import java.util.UUID;

@Data
public class ListPetsDTO {

    UUID uuid;

    String nome;

    String raca;

    String imagem;
}
