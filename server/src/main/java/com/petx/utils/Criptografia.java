package com.petx.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Criptografia{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${salt.password}")
    private String salt;

    public String criptogafarSenha(String senha){
        return passwordEncoder.encode(senha + salt);
    }

    public String getSenhaSalto(String senha){
        return senha + salt;
    }
}
