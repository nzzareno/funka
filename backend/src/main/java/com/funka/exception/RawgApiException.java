package com.funka.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Ejemplo de uso:
 *   throw new RawgApiException("Game not found", HttpStatus.NOT_FOUND);
 */
@Getter  // Lombok genera el getter para 'status'
public class RawgApiException extends RuntimeException {

    private final HttpStatus status;

    public RawgApiException(String message, HttpStatus status) {
        super(message);  // Llama al constructor de RuntimeException con el mensaje
        this.status = status;
    }

    public RawgApiException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);  // Llama al constructor de RuntimeException con mensaje y causa
        this.status = status;
    }
}