package com.sige.sistema_empenhos.exceptions;

public class EmpenhoNotFoundException extends RuntimeException{
    public EmpenhoNotFoundException(String message) {
        super(message);
    }
}
