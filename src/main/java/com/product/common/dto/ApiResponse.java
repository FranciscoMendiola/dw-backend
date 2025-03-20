package com.product.common.dto;
/**
 * Clase que representa una respuesta de la API con un mensaje.
 */
public class ApiResponse {

    /**
     * Mensaje de la respuesta.
     */
    private String message;

    /**
     * Constructor que inicializa la respuesta con un mensaje.
     *
     * @param message el mensaje de la respuesta
     */
    public ApiResponse(String message) {
        super();
        this.message = message;
    }

    /**
     * Obtiene el mensaje de la respuesta.
     *
     * @return el mensaje de la respuesta
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece un nuevo mensaje en la respuesta.
     *
     * @param message el nuevo mensaje de la respuesta
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
