package com.sige.sistema_empenhos.infra;

import com.sige.sistema_empenhos.exceptions.*;
import com.sige.sistema_empenhos.services.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmpenhoNotFoundException.class)
    private ResponseEntity<String> empenhoNotFoundHandler(EmpenhoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmpenhoInvalidDeleteException.class)
    private ResponseEntity<String> empenhoStatusHandler(EmpenhoInvalidDeleteException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> illegalArgumentHandler(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(EmpenhoInvalidStatusException.class)
    private ResponseEntity<String> empenhoInvalidStatusHandler(EmpenhoInvalidStatusException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(LiquidacaoNotFoundException.class)
    private ResponseEntity<String> liquidacaoNotFoundHandler(LiquidacaoNotFoundException exception){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(PagamentoNotFoundException.class)
    private ResponseEntity<String> pagamentoNotFoundHandler(PagamentoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
