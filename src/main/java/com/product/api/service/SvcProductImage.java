package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.common.dto.ApiResponse;

public interface SvcProductImage {

    public ResponseEntity<List<ProductImage>> getProductImages(Integer product_id);

    public ResponseEntity<ApiResponse> createProductImage(DtoProductImageIn in);

    public ResponseEntity<ApiResponse> deleteProductImage(Integer product_image_id);
}
