package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Modelo de Producto con un ID, gtin, nombre, descripción, precio, stock y
 * estado.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	@JsonProperty("productId")
	private Integer productId;

	@Column(name = "gtin")
	@JsonProperty("gtin")
	private String gtin;

	@Column(name = "product")
	@JsonProperty("product")
	private String product;

	@Column(name = "description")
	@JsonProperty("description")
	private String description;

	@Column(name = "price")
	@JsonProperty("price")
	private Double price;

	@Column(name = "stock")
	@JsonProperty("stock")
	private Integer stock;

	@Column(name = "category_id")
	@JsonProperty("categoryId")
	private Integer categoryId;

	@Column(name = "status")
	@JsonProperty("status")
	private Integer status;
}
