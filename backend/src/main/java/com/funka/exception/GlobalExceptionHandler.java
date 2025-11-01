package com.funka.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.funka.dto.error.ErrorResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones específicas de la API de RAWG
     */
    @ExceptionHandler(RawgApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleRawgApiException(RawgApiException ex, WebRequest request) {
        log.error("RAWG API Error: {} - Status: {}", ex.getMessage(), ex.getStatus());
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    /**
     * Maneja errores de conversión de tipos en parámetros
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error("Type mismatch error: {}", ex.getMessage());
        Class<?> requiredType = ex.getRequiredType();
        String expectedType = requiredType != null ? requiredType.getSimpleName() : "Unknown";
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s", ex.getValue(), ex.getName(), expectedType);
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de argumentos ilegales
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        log.error("Illegal argument error: {}", ex.getMessage());
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja todas las demás excepciones no controladas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, WebRequest request) {
        log.error("Unexpected error occurred: ", ex);
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An unexpected error occurred. Please try again later.")
                .path(getPath(request))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Extrae el path de la request
     */
    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}
