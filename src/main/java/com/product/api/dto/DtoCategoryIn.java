package com.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) para la creación y actualización de categorías de
 * productos.
 * Contiene los datos requeridos para definir una categoría.
 */
public class DtoCategoryIn {

    /**
     * Nombre de la categoría. Es un campo obligatorio.
     */
    @JsonProperty("category")
    @NotNull(message = "La categoría es obligatoria")
    private String category;

    /**
     * Etiqueta asociada a la categoría. Es un campo obligatorio.
     */
    @JsonProperty("tag")
    @NotNull(message = "El tag es obligatorio")
    private String tag;

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return el nombre de la categoría
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param category el nuevo nombre de la categoría
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtiene la etiqueta asociada a la categoría.
     *
     * @return la etiqueta de la categoría
     */
    public String getTag() {
        return tag;
    }

    /**
     * Establece la etiqueta de la categoría.
     *
     * @param tag la nueva etiqueta de la categoría
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
