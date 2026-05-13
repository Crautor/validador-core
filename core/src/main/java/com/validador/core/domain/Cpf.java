package com.validador.core.domain;

public record Cpf(String numero) implements Documento {

    public Cpf {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
    }

    @Override
    public boolean isValido() {
        String digits = numero.replaceAll("\\D", "");
        return digits.length() == 11;
    }
}
