package com.validador.core.service;

import com.validador.core.domain.Cnpj;
import com.validador.core.domain.Cpf;
import com.validador.core.domain.Documento;
import com.validador.core.domain.Ssn;

public class ValidadorDocumentoService {

    public ResultadoValidacao validar(Documento documento) {
        // Pattern Matching com switch exaustivo — o compilador garante que todos os
        // subtipos da sealed interface Documento são cobertos (Cpf, Cnpj, Ssn)
        return switch (documento) {
            case Cpf cpf   -> validarCpf(cpf);
            case Cnpj cnpj -> validarCnpj(cnpj);
            case Ssn ssn   -> validarSsn(ssn);
        };
    }

    private ResultadoValidacao validarCpf(Cpf cpf) {
        if (!cpf.isValido()) {
            return new ResultadoValidacao(false,
                    "CPF inválido: deve conter 11 dígitos numéricos", "CPF", cpf.numero());
        }
        return new ResultadoValidacao(true, "CPF válido", "CPF", cpf.numero());
    }

    private ResultadoValidacao validarCnpj(Cnpj cnpj) {
        if (!cnpj.isValido()) {
            return new ResultadoValidacao(false,
                    "CNPJ inválido: deve conter 14 dígitos numéricos", "CNPJ", cnpj.numero());
        }
        return new ResultadoValidacao(true, "CNPJ válido", "CNPJ", cnpj.numero());
    }

    private ResultadoValidacao validarSsn(Ssn ssn) {
        if (!ssn.isValido()) {
            return new ResultadoValidacao(false,
                    "SSN inválido: deve conter 9 dígitos numéricos", "SSN", ssn.numero());
        }
        return new ResultadoValidacao(true, "SSN válido", "SSN", ssn.numero());
    }
}
