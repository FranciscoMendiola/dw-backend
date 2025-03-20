package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

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

    /**
     * Inserta una nueva categoría en la base de datos con estado activo.
     *
     * @param category el nombre de la categoría
     * @param tag      la etiqueta asociada a la categoría
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO category (category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
    void createCategory(@Param("category") String category, @Param("tag") String tag);

    /**
     * Actualiza el nombre y la etiqueta de una categoría existente en la base de
     * datos.
     *
     * @param category_id el identificador único de la categoría
     * @param category    el nuevo nombre de la categoría
     * @param tag         la nueva etiqueta de la categoría
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id;", nativeQuery = true)
    void updateCategory(@Param("category_id") Integer category_id, @Param("category") String category,
            @Param("tag") String tag);

    /**
     * Actualiza el estado de una categoría en la base de datos.
     *
     * @param category_id el identificador único de la categoría
     * @param status      el nuevo estado de la categoría (1 para activo, 0 para
     *                    inactivo)
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET status = :status WHERE category_id = :category_id;", nativeQuery = true)
    void updateCategoryStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);

}
