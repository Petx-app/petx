package com.petx.domain.pet;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Entity
public class PetImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeImagem;

    @Transient
    private MultipartFile arquivo;

    @Transient
    private UUID uuidPet;

}
