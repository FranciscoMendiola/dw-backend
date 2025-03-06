package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.entity.Category;

/**
 * Servicio que implementa un repositorio de la base de datos para manejar las 
 * operaciones las categorías de productos.
 */
public interface SvcCategory {

    /**
     * Devuelve una lista de categorías de productos ordenadas por nombre de
     * categoría.
     *
     * @return ResponseEntity con una lista de objetos Category que representan
     *         categorías de productos
     */
    public ResponseEntity<List<Category>> getCategories();

    /**
     * Devuelve una lista de categorías de productos activas.
     *
     * @return ResponseEntity con una lista de objetos Category que representan
     *         categorías de productos activas
     */
    public ResponseEntity<List<Category>> getActiveCategories();

    /**
     * Devuelve una categoría de productos con el ID de categoría especificado.
     *
     * @param category_id el identificador único de la categoría
     * @return ResponseEntity con un objeto Category que representa la categoría de
     *         productos
     */
    public ResponseEntity<Category> getCategory(Integer category_id);
}