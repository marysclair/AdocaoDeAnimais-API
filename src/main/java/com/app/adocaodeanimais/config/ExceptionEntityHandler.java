package com.app.adocaodeanimais.config;

import com.app.adocaodeanimais.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleEventNotFound(NotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
