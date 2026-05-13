package com.validador.core.domain;

public sealed interface Documento permits Cpf, Cnpj, Ssn {
    String numero();
    boolean isValido();
}
