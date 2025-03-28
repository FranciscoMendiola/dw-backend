package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

    @Query(value = "SELECT * FROM product_image WHERE product_id = :product_id;", nativeQuery = true)
	ProductImage findByProduct_id(Integer product_id);
}
