package com.sige.sistema_empenhos.exceptions;

public class EmpenhoInvalidStatusException extends RuntimeException {
    public EmpenhoInvalidStatusException(String message) {
        super(message);
    }
}
