package com.product.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa una respuesta de la API con un mensaje.
 */
@Getter
@Setter
public class ApiResponse {

    /**
     * Mensaje de la respuesta.
     */
    private String message;

    /**
     * Constructor que inicializa la respuesta con un mensaje.
     *
     * @param message el mensaje de la respuesta
     * @throws IllegalArgumentException si el mensaje es nulo o vacío
     */
    public ApiResponse(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede ser vacío");
        }
        this.message = message;
    }
}
