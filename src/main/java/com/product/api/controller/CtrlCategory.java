package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;


/**
 * Controlador REST que maneja solicitudes HTTP para categorías de productos.
 * Proporciona un endpoint para recuperar una lista de categorías de productos
 * predefinida.
 */
@NoArgsConstructor
@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Catálogo de categorías de productos")
public class CtrlCategory {

	/*
	 * El servicio que maneja las operaciones de base de datos para las categorías
	 * de productos.
	 */
	@Autowired
	private SvcCategory svc;

	/**
	 * Maneja solicitudes GET para recuperar una lista de categorías de productos.
	 *
	 * @return una lista de objetos Category que representan categorías de productos
	 *         predefinida.
	 */
	@GetMapping
	@Operation(summary = "Consultar categorías", description = "Retorna una lista con todas las categorías de productos registradas")
	public ResponseEntity<List<Category>> getCategories() {
		return svc.getCategories();
	}

	/**
	 * Maneja solicitudes GET para recuperar una lista de categorías de productos
	 * activas.
	 *
	 * @return una lista de objetos Category que representan categorías de productos
	 *         activas.
	 */
	@GetMapping("/active")
	@Operation(summary = "Consultar categorías activas", description = "Retorna una lista con todas las categorías activas de productos registradas")
	public ResponseEntity<List<Category>> getActiveCategories() {
		return svc.getActiveCategories();
	}

	/**
	 * Maneja solicitudes GET para recuperar una categoría de productos con el ID de
	 * categoría especificado.
	 *
	 * @param categoryId el identificador único de la categoría
	 * @return un objeto Category que representa la categoría de productos
	 */
	@GetMapping("/{categoryId}")
	@Operation(summary = "Consultar categoría por id", description = "Retorna la categoría asociada al id")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId) {
		return svc.getCategory(categoryId);
	}

	/**
	 * Maneja solicitudes POST para crear una nueva categoría de productos.
	 *
	 * @param in            los datos de entrada de la nueva categoría
	 * @param bindingResult el resultado de la validación de los datos de entrada
	 * @return una respuesta API indicando el resultado de la operación
	 * @throws ApiException si los datos de entrada no son válidos
	 */
	@PostMapping
	@Operation(summary = "Registrar categoría", description = "Registra una nueva categoría de productos")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody DtoCategoryIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST,
					bindingResult.getAllErrors().get(0).getDefaultMessage());
		return svc.createCategory(in);
	}

	/**
	 * Maneja solicitudes PUT para actualizar una categoría de productos existente.
	 *
	 * @param categoryId    el identificador único de la categoría a actualizar
	 * @param in            los nuevos datos de la categoría
	 * @param bindingResult el resultado de la validación de los datos de entrada
	 * @return una respuesta API indicando el resultado de la operación
	 * @throws ApiException si los datos de entrada no son válidos
	 */
	@PutMapping("/{categoryId}")
	@Operation(summary = "Actualizar categoría", description = "Actualiza la categoría asociada al id")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId,
					@Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST,
					bindingResult.getAllErrors().get(0).getDefaultMessage());
		return svc.updateCategory(categoryId, in);
	}

	/**
	 * Maneja solicitudes PATCH para habilitar una categoría de productos
	 * específica.
	 *
	 * @param categoryId el identificador único de la categoría a habilitar
	 * @return una respuesta API indicando el resultado de la operación
	 */
	@PatchMapping("/{categoryId}/enable")
	@Operation(summary = "Habilitar categoría", description = "Habilita la categoría asociada al id")
	public ResponseEntity<ApiResponse> enableCategory(@PathVariable("categoryId") Integer categoryId) {
		return svc.enableCategory(categoryId);
	}

	/**
	 * Maneja solicitudes PATCH para deshabilitar una categoría de productos
	 * específica.
	 *
	 * @param categoryId el identificador único de la categoría a deshabilitar
	 * @return una respuesta API indicando el resultado de la operación
	 */
	@PatchMapping("/{categoryId}/disable")
	@Operation(summary = "Deshabilitar categoría", description = "Deshabilita la categoría asociada al id")
	public ResponseEntity<ApiResponse> disableCategory(@PathVariable("categoryId") Integer categoryId) {
		return svc.disableCategory(categoryId);
	}
}