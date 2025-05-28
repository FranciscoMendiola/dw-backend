package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO (Data Transfer Object) para la creación y actualización de categorías de
 * productos.
 * Contiene los datos requeridos para definir una categoría.
 */
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DtoCategoryIn {

    /**
     * Nombre de la categoría. Es un campo obligatorio.
     */
    @NotNull(message = "La categoría es obligatoria")
    @JsonProperty("category")
    private String category;

    /**
     * Etiqueta asociada a la categoría. Es un campo obligatorio.
     */
    @NotNull(message = "El tag es obligatorio")
    @JsonProperty("tag")
    private String tag;
}
