package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

    @Query(value = "SELECT pi.* " +
            "FROM product_image pi " +
            "INNER JOIN product p ON pi.product_id = p.product_id " +
            "WHERE pi.product_id = :product_id " +
            "AND pi.status = 1 " +
            "AND p.status = 1", nativeQuery = true)
    public List<ProductImage> findByProductId(Integer product_id);
}
