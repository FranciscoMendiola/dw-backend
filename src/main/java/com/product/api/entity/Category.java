package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Modelo de Categoría con un ID, nombre, etiqueta y estado.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    /* El identificador único de la categoría. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @JsonProperty("categoryId")
    private Integer categoryId;
    /* El nombre de la categoría. */
    @Column(name = "category")
    @JsonProperty("category")
    private String category;
    /* La etiqueta asociada con la categoría. */
    @Column(name = "tag")
    @JsonProperty("tag")
    private String tag;
    /* El estado de la categoría. */
    @Column(name = "status")
    @JsonProperty("status")
    private Integer status;
}