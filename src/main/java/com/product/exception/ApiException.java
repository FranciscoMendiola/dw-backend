package com.product.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción genérica para la API de productos.
 */
public class ApiException extends RuntimeException {
    
    /* Serial version UID. */
    private static final long serialVersionUID = 1L;
    /* Estado HTTP de la excepción. */
    private HttpStatus status;
    
    /**
     * Constructor para ApiException que recibe un estado HTTP y un mensaje.
     * 
     * @param status Estado HTTP de la excepción
     * @param message Mensaje de la excepción
     */
    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    
    /**
     * Devuelve el serial version UID.
     * 
     * @return el serial version UID
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Devuelve el estado HTTP de la excepción.
     * 
     * @return el estado HTTP de la excepción
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado HTTP de la excepción.
     * 
     * @param status el nuevo estado HTTP de la excepción
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
