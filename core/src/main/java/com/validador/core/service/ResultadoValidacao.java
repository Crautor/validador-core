package com.validador.core.service;

public record ResultadoValidacao(
        boolean valido,
        String mensagem,
        String tipoDocumento,
        String numeroDocumento
) {}
