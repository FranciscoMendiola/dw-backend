package com.product.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.common.dto.ApiResponse;

@Service
public interface SvcProductImage {

    public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);

}
