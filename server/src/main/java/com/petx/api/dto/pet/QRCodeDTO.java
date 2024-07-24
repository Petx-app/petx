package com.petx.api.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QRCodeDTO {

    String nomePet;

    String especie;

    String nomeDono;

    String telefoneDono;

    String imagemPet;
}