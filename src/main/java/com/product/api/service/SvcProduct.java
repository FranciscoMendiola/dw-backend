package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.in.DtoStockIn;
import com.product.api.dto.in.DtoValidateProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.common.dto.ApiResponse;

public interface SvcProduct {

	public ResponseEntity<List<DtoProductListOut>> getProducts();

	public ResponseEntity<List<DtoProductListOut>> getActiveProducts();

	public ResponseEntity<List<DtoProductListOut>> getProductsByCategory(Integer categoryId);

	public ResponseEntity<DtoProductOut> getProduct(Integer productId);

	public ResponseEntity<ApiResponse> createProduct(DtoProductIn in);

	public ResponseEntity<ApiResponse> updateProduct(Integer productId, DtoProductIn in);

	public ResponseEntity<ApiResponse> updateProductStock(Integer productId, DtoStockIn in);

	public ResponseEntity<ApiResponse> enableProduct(Integer productId);

	public ResponseEntity<ApiResponse> disableProduct(Integer productId);

	public ResponseEntity<Integer> validateProduct(DtoValidateProductIn in);
}
