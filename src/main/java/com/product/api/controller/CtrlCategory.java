package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

/**
 * Controlador REST que maneja solicitudes HTTP para categorías de productos.
 * Proporciona un endpoint para recuperar una lista de categorías de productos predefinida.
 */
@RestController
@RequestMapping("/category")
public class CtrlCategory {

	/* El servicio que maneja las operaciones de base de datos para las categorías de productos. */
	@Autowired
	private SvcCategory svc;

	/**
	 * Constructor por defecto para CtrlProduct.
	 */
	public CtrlCategory() {}

	/**
	 * Maneja solicitudes GET para recuperar una lista de categorías de productos.
	 *
	 * @return una lista de objetos Category que representan categorías de productos predefinida.
	 */
	@GetMapping
	public List<Category> getCategories() {
		return svc.getCategories();
	}

	/**
	 * Maneja solicitudes GET para recuperar una lista de categorías de productos activas.
	 *
	 * @return una lista de objetos Category que representan categorías de productos activas.
	 */
	@GetMapping("/active")
	public List<Category> getActiveCategories() {
		return svc.getActiveCategories();
	}

	/**
	 * Maneja solicitudes GET para recuperar una categoría de productos con el ID de categoría especificado.
	 *
	 * @param category_id el identificador único de la categoría
	 * @return un objeto Category que representa la categoría de productos
	 */
	@GetMapping("/{category_id}")
	public Category getCategory(@PathVariable Integer category_id) {
		return svc.getCategory(category_id);
	}
}
