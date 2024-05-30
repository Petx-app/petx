package com.petx.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@ActiveProfiles("test")
public class ValidarTempoDeExpiracao {

    @Test
    public void tempoDeExpiracaoValido() {
        LocalDateTime horaAtual = LocalDateTime.of(2024, 5, 17, 12, 30);
        LocalDateTime horaRegistradaParaValidar = LocalDateTime.of(2024, 5, 17, 12, 30);

        //horas iguais
        if (horaRegistradaParaValidar.toLocalDate().isEqual(horaAtual.toLocalDate())
                && ChronoUnit.MINUTES.between(horaRegistradaParaValidar, horaAtual) <= 2) {
            Assertions.assertTrue(true, "hora valida");
        }else{
            Assertions.assertTrue(false, "codigo expirado");
        }
    }

    @Test
    public void tempoDeExpiracaoExcedidoPorHoraDiferente() {
        LocalDateTime horaAtual = LocalDateTime.of(2024, 5, 17, 12, 33);
        LocalDateTime horaRegistradaParaValidar = LocalDateTime.of(2024, 5, 17, 12, 30);

        //horas diferente
        if (horaRegistradaParaValidar.toLocalDate().isEqual(horaAtual.toLocalDate())
                && ChronoUnit.MINUTES.between(horaRegistradaParaValidar, horaAtual) <= 2) {
            Assertions.assertTrue(false, "hora valida");
        }else{
            Assertions.assertTrue(true, "codigo expirado");
        }
    }

    @Test
    public void tempoDeExpiracaoExcedidoPorDataDifente() {
        LocalDateTime horaAtual = LocalDateTime.of(2024, 5, 20, 12, 33);
        LocalDateTime horaRegistradaParaValidar = LocalDateTime.of(2024, 5, 17, 12, 30);

        //Datas diferentes
        if (horaRegistradaParaValidar.toLocalDate().isEqual(horaAtual.toLocalDate())
                && ChronoUnit.MINUTES.between(horaRegistradaParaValidar, horaAtual) <= 2) {
            Assertions.assertTrue(false, "hora valida");
        }else{
            Assertions.assertTrue(true, "codigo expirado");
        }
    }
}