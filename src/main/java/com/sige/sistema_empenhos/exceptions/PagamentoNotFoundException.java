package com.sige.sistema_empenhos.exceptions;

public class PagamentoNotFoundException extends RuntimeException {
    public PagamentoNotFoundException(String message) {
        super(message);
    }
}
