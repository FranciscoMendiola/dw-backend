package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.common.dto.ApiResponse;

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

     /**
     * Crea una nueva categoría de productos con los datos proporcionados.
     *
     * @param in objeto DtoCategoryIn que contiene el nombre y el tag de la
     *           categoría
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     */
    public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in);

    /**
     * Actualiza los datos de una categoría existente con el ID especificado.
     *
     * @param category_id el identificador único de la categoría a actualizar
     * @param in          objeto DtoCategoryIn con los nuevos datos de la categoría
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     */
    public ResponseEntity<ApiResponse> updateCategory(Integer category_id, DtoCategoryIn in);

    /**
     * Habilita una categoría de productos con el ID especificado.
     *
     * @param category_id el identificador único de la categoría a habilitar
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     */
    public ResponseEntity<ApiResponse> enableCategory(Integer category_id);
    
    /**
     * Deshabilita una categoría de productos con el ID especificado.
     *
     * @param category_id el identificador único de la categoría a deshabilitar
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     */
    public ResponseEntity<ApiResponse> disableCategory(Integer category_id);
}