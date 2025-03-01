package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;

/**
 * Implementación del servicio que maneja las operaciones de base de datos para las categorías de productos.
 */
@Service
public class SvcCategoryImp implements SvcCategory {
    
    /* El repositorio que maneja las operaciones de base de datos para las categorías de productos. */
    @Autowired
    private RepoCategory repo;

    /**
     * Constructor por defecto para SvcCategoryImp.
     */
    SvcCategoryImp() {}

    /**
     * Devuelve una lista de categorías de productos ordenadas por nombre de categoría.
     *
     * @return una lista de objetos Category que representan categorías de productos
     */
    @Override
    public List<Category> getCategories() {
        return repo.getCategories();
    }

    /**
     * Devuelve una lista de categorías de productos activas.
     *
     * @return una lista de objetos Category que representan categorías de productos activas
     */
    @Override
    public List<Category> getActiveCategories() {
        return repo.getActiveCategories();
    }

    /**
     * Devuelve una categoría de productos con el ID de categoría especificado.
     *
     * @param category_id el identificador único de la categoría
     * @return un objeto Category que representa la categoría de productos
     */
    @Override
    public Category getCategory(Integer category_id) {
        return repo.getCategory(category_id);
    }
}