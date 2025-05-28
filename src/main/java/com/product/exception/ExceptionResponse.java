package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de respuesta de excepción para la API de productos.
 */
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    /* Fecha y hora de la excepción. */
    /* Formato de fecha y hora. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    /* Estado HTTP de la excepción. */
    private Integer status;
    /* Error HTTP de la excepción. */
    private HttpStatus error;
    /* Mensaje de la excepción. */
    private String message;
    /* Ruta de la excepción. */
    private String path;
}
