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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RestController
@RequestMapping("/product-image")
@Tag(name = "Product Image", description = "Catálogo de imágenes de productos")
public class CtrlProductImage {

    @Autowired
    private SvcProductImage svc;

    @GetMapping("/{productId}")
    @Operation(summary = "Consultar imágenes de producto", description = "Retorna una lista con todas las imágenes asociadas al id del producto")
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable("productId") Integer productId) {
        return svc.getProductImages(productId);
    }

    @PatchMapping("/{productImageId}")
    @Operation(summary = "Deshabilitar imagen", description = "Deshabilita la imagen asociada al id")
    public ResponseEntity<ApiResponse> deleteProductImage(@PathVariable("productImageId") Integer productImageId) {
        return svc.deleteProductImage(productImageId);
    }

    @PostMapping
    @Operation(summary = "Registrar imagen", description = "Registra y asocia una nueva imagen como cadena en **base64** al id del producto")
    public ResponseEntity<ApiResponse> createProductImage(@Valid @RequestBody DtoProductImageIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return svc.createProductImage(in);
    }
}
