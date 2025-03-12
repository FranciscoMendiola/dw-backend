package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

/**
 * Implementación del servicio que maneja las operaciones de base de datos para
 * las categorías de productos.
 */
@Service
public class SvcCategoryImp implements SvcCategory {

    /* Repositorio que maneja las operaciones de base de datos. */
    @Autowired
    private RepoCategory repo;

    /**
     * Constructor por defecto para SvcCategoryImp.
     */
    SvcCategoryImp() {}

    /**
     * Devuelve una lista de categorías de productos ordenadas por nombre de
     * categoría.
     *
     * @return ResponseEntity con una lista de objetos Category que representan
     *         categorías de productos
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<List<Category>> getCategories() {
        try {
            return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Devuelve una lista de categorías de productos activas.
     *
     * @return ResponseEntity con una lista de objetos Category que representan
     *         categorías de productos activas
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<List<Category>> getActiveCategories() {
        try {
            return new ResponseEntity<>(repo.getActiveCategories(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Devuelve una categoría de productos con el ID de categoría especificado.
     *
     * @param category_id el identificador único de la categoría
     * @return ResponseEntity con un objeto Category que representa la categoría de
     *         productos
     * @throws ApiException si el ID de la categoría no existe
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<Category> getCategory(Integer category_id) {
        try {
            Category category = repo.getCategory(category_id);
            if (category == null) {
                throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
            }
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
}
