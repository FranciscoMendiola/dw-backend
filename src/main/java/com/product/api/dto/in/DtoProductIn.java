package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DtoProductIn {

	@JsonProperty("gtin")
	@Pattern(regexp = "^\\+?\\d{13}$", message = "El gtin tiene un formato inválido")
	@NotNull(message="El gtin es obligatorio")
	private String gtin;
	
	@JsonProperty("product")
	@NotNull(message="El product es obligatorio")
	private String product;
	
	@JsonProperty("description")
	@NotNull(message="El description es obligatorio")
	private String description;
	
	@JsonProperty("price")
	@Min(value = 0)
	@NotNull(message="El price es obligatorio")
	private Float price;
	
	@JsonProperty("stock")
	@NotNull(message="El stock es obligatorio")
	private Integer stock;

	@JsonProperty("category_id")
	@NotNull(message="El category_id es obligatorio")
	private Integer category_id;

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
}
