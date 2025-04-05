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
			+ "WHERE p.product_id = :product_id;", nativeQuery = true)
	public DtoProductOut getProduct(Integer product_id);

	@Query(value = "SELECT * FROM product WHERE status = 1", nativeQuery = true)
	public List<Product> getActiveProducts();

	@Query(value = "SELECT * FROM product WHERE category_id = :category_id", nativeQuery = true)
	public List<Product> getProductsByCategory(@Param("category_id") Integer category_id);

}
