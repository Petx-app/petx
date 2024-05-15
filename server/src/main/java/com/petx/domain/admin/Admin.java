package com.petx.domain.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Admin {

    @Id
    private Long id;

    private String usuario;

    private String senha;
}