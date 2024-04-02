package com.petx.api.exceptions;

public class PetNotFound extends Exception {
    public PetNotFound (String mensagem){
        super(mensagem);
    }
}
