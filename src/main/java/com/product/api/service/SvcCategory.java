package com.product.api.service;

import java.util.List;

import com.product.api.entity.Category;

/**
 * Servicio que maneja las operaciones de base de datos para las categorías de productos.
 */
public interface SvcCategory {

    /**
     * Devuelve una lista de categorías de productos ordenadas por nombre de categoría.
     *
     * @return una lista de objetos Category que representan categorías de productos
     */
    public List<Category> getCategories();

    /**
     * Devuelve una lista de categorías de productos activas.
     *
     * @return una lista de objetos Category que representan categorías de productos activas
     */
    public List<Category> getActiveCategories();

    /**
     * Devuelve una categoría de productos con el ID de categoría especificado.
     *
     * @param category_id el identificador único de la categoría
     * @return un objeto Category que representa la categoría de productos
     */
    public Category getCategory(Integer category_id);
}