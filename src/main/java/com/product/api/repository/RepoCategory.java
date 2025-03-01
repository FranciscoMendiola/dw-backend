package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

/**
 * Repositorio que maneja las operaciones de base de datos para las categorías de productos.
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {

    /**
     * Devuelve una lista de categorías de productos ordenadas por nombre de categoría.
     *
     * @return una lista de objetos Category que representan categorías de productos
     */
    @Query(value ="SELECT * FROM category ORDER BY category", nativeQuery = true)
    public List<Category> getCategories();
    
    /**
     * Devuelve una lista de categorías de productos activas.
     *
     * @return una lista de objetos Category que representan categorías de productos activas
     */
    @Query(value = "SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
    public List<Category> getActiveCategories();
    
    /**
     * Devuelve una categoría de productos con el ID de categoría especificado.
     *
     * @param category_id el identificador único de la categoría
     * @return un objeto Category que representa la categoría de productos
     */
    @Query(value = "SELECT * FROM category WHERE category_id = :category_id", nativeQuery = true)
    public Category getCategory(@Param("category_id") Integer category_id);
}
