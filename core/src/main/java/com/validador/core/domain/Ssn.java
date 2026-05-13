package com.validador.core.domain;

public record Ssn(String numero) implements Documento {

    public Ssn {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("SSN não pode ser nulo ou vazio");
        }
    }

    @Override
    public boolean isValido() {
        String digits = numero.replaceAll("[-\\s]", "");
        return digits.length() == 9;
    }
}
