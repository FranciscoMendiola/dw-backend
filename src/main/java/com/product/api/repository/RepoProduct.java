package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT p.*, c.category "
			+ "FROM product p "
			+ "INNER JOIN category c ON c.category_id = p.category_id "
			+ "WHERE p.product_id = :productId;", nativeQuery = true)
	public DtoProductOut getProduct(@Param("productId") Integer productId);

	@Query(value = "SELECT * FROM product WHERE status = 1", nativeQuery = true)
	public List<Product> getActiveProducts();

	@Query(value = "SELECT * FROM product WHERE category_id = :categoryId", nativeQuery = true)
	public List<Product> getProductsByCategory(@Param("categoryId") Integer categoryId);

	@Query(value = "SELECT * FROM product WHERE product = :product LIMIT 1", nativeQuery = true)
	public Product findByProduct(@Param("product") String product);

}
