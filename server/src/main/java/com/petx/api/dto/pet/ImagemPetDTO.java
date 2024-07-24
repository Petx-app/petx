package com.petx.api.dto.pet;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ImagemPetDTO {

    private MultipartFile imagemPet;

    private UUID uuid;
}
