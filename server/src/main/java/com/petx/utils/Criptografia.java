package com.petx.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class Criptografia{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${salt.password}")
    private String terra;

    public String criptogafarSenha(String senha, UUID semente){
        return passwordEncoder.encode(terra + senha + semente.toString());
    }

    public String getSenhaSalto(String senha, UUID semente){
        return terra + senha + semente.toString();
    }
}