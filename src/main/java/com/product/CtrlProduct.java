package com.product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que maneja solicitudes HTTP para categorías de productos.
 * Proporciona un endpoint para recuperar una lista de categorías de productos predefinida.
 */
@RestController
@RequestMapping("/category")
public class CtrlProduct {

	/**
	 * Constructor por defecto para CtrlProduct.
	 */
	public CtrlProduct() {}

	/**
	 * Maneja solicitudes GET para recuperar una lista de categorías de productos.
	 *
	 * @return una lista de objetos Category que representan categorías de productos predefinida.
	 */
	@GetMapping
	public List<Category> getCategories() {
		return List.of(
				new Category(1, "Lentes", "Lts", 1),
				new Category(2, "Relojes", "Rljs", 1),
				new Category(3, "Zapatos", "Zpts", 1),
				new Category(4, "Camisetas", "Cms", 1),
				new Category(5, "Pantalones", "Pnts", 1),
				new Category(6, "Sombreros", "Smbrs", 1));
	}
}
