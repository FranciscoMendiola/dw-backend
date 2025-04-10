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

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.service.SvcProduct;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Catálogo de productos")
public class CtrlProduct {

	@Autowired
	private SvcProduct svc;

	@GetMapping
	@Operation(summary = "Consultar productos", description = "Retorna una lista con todos los productos registrados")
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		return svc.getProducts();
	}

	@GetMapping("/active")
	@Operation(summary = "Consultar productos activos", description = "Retorna una lista con todos los productos activos registrados")
	public ResponseEntity<List<DtoProductListOut>> getActiveProducts() {
		return svc.getActiveProducts();
	}

	@GetMapping("/category/{categoryId}")
	@Operation(summary = "Consultar productos por categoría", description = "Retorna una lista con todos los productos asociados al id de la categoría")
	public ResponseEntity<List<DtoProductListOut>> getProductsByCategory(
			@PathVariable("categoryId") Integer categoryId) {
		return svc.getProductsByCategory(categoryId);
	}

	@GetMapping("/{productId}")
	@Operation(summary = "Consultar producto por id", description = "Retorna el producto asociado al id")
	public ResponseEntity<DtoProductOut> getProduct(@PathVariable("productId") Integer productId) {
		return svc.getProduct(productId);
	}

	@PostMapping
	@Operation(summary = "Registrar producto", description = "Registra un nuevo producto")
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody DtoProductIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.createProduct(in);
	}

	@PutMapping("/{productId}")
	@Operation(summary = "Actualizar producto", description = "Actualiza el producto asociado al id")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,
			@Valid @RequestBody DtoProductIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.updateProduct(productId, in);
	}

	@PatchMapping("/{productId}/stock")
	@Operation(summary = "Actualizar stock de producto", description = "Actualiza el stock del producto asociado al id")
	public ResponseEntity<ApiResponse> updateProductStock(@PathVariable("productId") Integer productId,
			@Valid @RequestBody Integer newStock) {
		return svc.updateProductStock(productId, newStock);
	}

	@PatchMapping("/{productId}/enable")
	@Operation(summary = "Habilitar producto", description = "Habilita el producto asociado al id")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable("productId") Integer productId) {
		return svc.enableProduct(productId);
	}

	@PatchMapping("/{productId}/disable")
	@Operation(summary = "Deshabilitar producto", description = "Deshabilita el producto asociado al id")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable("productId") Integer productId) {
		return svc.disableProduct(productId);
	}
}
