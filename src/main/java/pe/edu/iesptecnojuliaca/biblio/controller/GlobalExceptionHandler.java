package pe.edu.iesptecnojuliaca.biblio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> datosInvalidos(IllegalArgumentException ex) {
        log.warn("400 - datos inválidos: {}", ex.getMessage());
        return respuesta(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> jsonInvalido(HttpMessageNotReadableException ex) {
        Throwable causa = ex.getMostSpecificCause();
        log.warn("400 - JSON no se pudo leer: {}", causa.getMessage());
        return respuesta("No se pudo leer el JSON enviado: " + causa.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> parametroInvalido(MethodArgumentTypeMismatchException ex) {
        log.warn("400 - parámetro inválido '{}': {}", ex.getName(), ex.getValue());
        return respuesta("Valor inválido para '" + ex.getName() + "': " + ex.getValue());
    }

    private ResponseEntity<Map<String, String>> respuesta(String mensaje) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", mensaje));
    }
}