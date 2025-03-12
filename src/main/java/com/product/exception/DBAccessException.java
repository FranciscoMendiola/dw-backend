package com.product.exception;

import org.springframework.dao.DataAccessException;

/**
 * Excepción personalizada para errores de acceso a la base de 
 * datos para la API de productos.
 */
public class DBAccessException extends RuntimeException {
    
    /* Serial version UID. */
    private static final long serialVersionUID = 1L;
    /* Excepción de acceso a datos. */
    private DataAccessException exception;
    
    /**
     * Constructor para DBAccessException que recibe una excepción de acceso a 
     * datos.
     * 
     * @param e la excepción de acceso a datos
     */
    public DBAccessException(DataAccessException e) {
        this.exception = e;
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
     * Devuelve la excepción de acceso a datos.
     * 
     * @return la excepción de acceso a datos
     */
    public DataAccessException getException() {
        return exception;
    }

    /**
     * Establece la excepción de acceso a datos.
     * 
     * @param exception la nueva excepción de acceso a datos
     */
    public void setException(DataAccessException exception) {
        this.exception = exception;
    }    
}