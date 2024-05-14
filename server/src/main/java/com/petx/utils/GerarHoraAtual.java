package com.petx.utils;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class GerarHoraAtual {

    public static int horaAtual(){

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String hora = currentTime.format(formatter).replace(":","");

        return Integer.parseInt(hora);
    }
}
