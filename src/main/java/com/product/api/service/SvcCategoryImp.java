package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

import lombok.NoArgsConstructor;

/**
 * Implementación del servicio que maneja las operaciones de base de datos para
 * las categorías de productos.
 */
@NoArgsConstructor
@Service
public class SvcCategoryImp implements SvcCategory {

    /* Repositorio que maneja las operaciones de base de datos. */
    @Autowired
    private RepoCategory repo;

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
     * @param categoryId el identificador único de la categoría
     * @return ResponseEntity con un objeto Category que representa la categoría de
     *         productos
     * @throws ApiException      si el ID de la categoría no existe
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<Category> getCategory(Integer categoryId) {
        try {
            Category category = validateCategoryId(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Crea una nueva categoría de productos con los datos proporcionados.
     *
     * @param in objeto DtoCategoryIn que contiene el nombre y el tag de la
     *           categoría
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     * @throws ApiException      si el nombre o el tag de la categoría ya están
     *                           registrados
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in) {
        try {
            repo.createCategory(in.getCategory(), in.getTag());
            return new ResponseEntity<>(new ApiResponse("La categoría ha sido registrada"), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            String msg = e.getLocalizedMessage();
            if (msg != null) {
                if (msg.contains("ux_category_category")) {
                    throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
                } else if (msg.contains("ux_category_tag")) {
                    throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
                }
            }
            throw new DBAccessException(e);
        }
    }

    /**
     * Actualiza los datos de una categoría existente con el ID especificado.
     *
     * @param categoryId el identificador único de la categoría a actualizar
     * @param in         objeto DtoCategoryIn con los nuevos datos de la categoría
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     * @throws ApiException      si el ID de la categoría no existe o si el
     *                           nombre/tag ya están registrados
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<ApiResponse> updateCategory(Integer categoryId, DtoCategoryIn in) {
        try {
            validateCategoryId(categoryId);
            repo.updateCategory(categoryId, in.getCategory(), in.getTag());
            return new ResponseEntity<>(new ApiResponse("La categoria ha sido actualizada"), HttpStatus.OK);
        } catch (DataAccessException e) {
            String msg = e.getLocalizedMessage();
            if (msg != null) {
                if (msg.contains("ux_category_category")) {
                    throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
                } else if (msg.contains("ux_category_tag")) {
                    throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
                }
            }
            throw new DBAccessException(e);
        }
    }

    /**
     * Habilita una categoría de productos con el ID especificado.
     *
     * @param categoryId el identificador único de la categoría a habilitar
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     * @throws ApiException      si el ID de la categoría no existe
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<ApiResponse> enableCategory(Integer categoryId) {
        try {
            validateCategoryId(categoryId);
            repo.updateCategoryStatus(categoryId, 1);
            return new ResponseEntity<>(new ApiResponse("La categoria ha sido activada"), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Deshabilita una categoría de productos con el ID especificado.
     *
     * @param categoryId el identificador único de la categoría a deshabilitar
     * @return ResponseEntity con un objeto ApiResponse que indica el resultado de
     *         la operación
     * @throws ApiException      si el ID de la categoría no existe
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    @Override
    public ResponseEntity<ApiResponse> disableCategory(Integer categoryId) {
        try {
            validateCategoryId(categoryId);
            repo.updateCategoryStatus(categoryId, 0);
            return new ResponseEntity<>(new ApiResponse("La categoria ha sido desactivada"), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Valida si una categoría con el ID especificado existe en la base de datos.
     *
     * @param categoryId el identificador único de la categoría a validar
     * @return la category asociada al categoryId si existe
     * @throws ApiException      si el ID de la categoría no existe
     * @throws DBAccessException si hay un error de acceso a la base de datos
     */
    private Category validateCategoryId(Integer categoryId) {
        Category category;
        try {
            category = repo.getCategory(categoryId);

            if (category == null) {
                throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoria no existe");
            }
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
        return category;
    }
}
