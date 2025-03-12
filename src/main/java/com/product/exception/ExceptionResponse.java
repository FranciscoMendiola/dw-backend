package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Modelo de respuesta de excepción para la API de productos.
 */
public class ExceptionResponse {

    /* Fecha y hora de la excepción. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    /* Formato de fecha y hora. */
    private LocalDateTime timestamp;
    /* Estado HTTP de la excepción. */
    private Integer status;
    /* Error HTTP de la excepción. */
    private HttpStatus error;
    /* Mensaje de la excepción. */
    private String message;
    /* Ruta de la excepción. */
    private String path;
    
    /**
     * Constructor por defecto para ExceptionResponse.
     */
    public ExceptionResponse() {};
    
    /**
     * Devuelve la fecha y hora de la excepción.
     * 
     * @return la fecha y hora de la excepción
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la fecha y hora de la excepción.
     * 
     * @param timestamp la nueva fecha y hora de la excepción
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Devuelve el estado HTTP de la excepción.
     * 
     * @return el estado HTTP de la excepción
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece el estado HTTP de la excepción.
     * 
     * @param status el nuevo estado HTTP de la excepción
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * Devuelve el error HTTP de la excepción.
     * 
     * @return el error HTTP de la excepción
     */
    public HttpStatus getError() {
        return error;
    }

    /**
     * Establece el error HTTP de la excepción.
     * 
     * @param error el nuevo error HTTP de la excepción
     */
    public void setError(HttpStatus error) {
        this.error = error;
    }
    
    /**
     * Devuelve el mensaje de la excepción.
     * 
     * @return el mensaje de la excepción
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de la excepción.
     * 
     * @param message el nuevo mensaje de la excepción
     */
    public void setMessage(String message) {
        this.message = message;
    
    }

    /**
     * Devuelve la ruta de la excepción.
     * 
     * @return la ruta de la excepción
     */
    public String getPath() {
        return path;
    }

    /**
     * Establece la ruta de la excepción.
     * 
     * @param path la nueva ruta de la excepción
     */
    public void setPath(String path) {
        this.path = path;
    }
}
