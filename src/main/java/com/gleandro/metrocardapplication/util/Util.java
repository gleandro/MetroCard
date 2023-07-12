package com.gleandro.metrocardapplication.util;

import java.util.Random;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatCodeNumber(long number) {
        return String.format("%03d", ++number);
    }

    public static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Primer dígito debe ser 4 para cumplir con el formato de Visa
        sb.append("4");

        // Generar los 15 dígitos restantes
        for (int i = 0; i < 15; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
