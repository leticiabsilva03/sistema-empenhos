package com.sige.sistema_empenhos.Infra;

import com.sige.sistema_empenhos.exceptions.EmpenhoNotFoundException;
import com.sige.sistema_empenhos.exceptions.EmpenhoStatusException;
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

    @ExceptionHandler(EmpenhoStatusException.class)
    private ResponseEntity<String> empenhoStatusHandler(EmpenhoStatusException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}
