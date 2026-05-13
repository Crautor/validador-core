package com.validador.core.domain;

public record Cnpj(String numero) implements Documento {

    public Cnpj {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio");
        }
    }

    @Override
    public boolean isValido() {
        String digits = numero.replaceAll("\\D", "");
        return digits.length() == 14;
    }
}
