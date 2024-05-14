package com.petx.utils;

import com.petx.domain.usuario.ValidacaoEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class GerarCodigoVerificacaoEmail {

    @Autowired
    private GerarHoraAtual gerarHoraAtual;

    private static final String characteres = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
    private static final int length = 4;

    public ValidacaoEmail GerarCodigoVerificacaoEmail(String email){
        int horaAtual = gerarHoraAtual.horaAtual();
        String codigoValidacao = gerarCodigo();

        ValidacaoEmail validacaoEmail = new ValidacaoEmail();
        validacaoEmail.setCodigo(codigoValidacao);
        validacaoEmail.setHoraInserida(horaAtual);
        validacaoEmail.setEmail(email.toLowerCase());

        return validacaoEmail;
    }

    private static String gerarCodigo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characteres.length());
            sb.append(characteres.charAt(index));
        }

        return sb.toString();
    }
}