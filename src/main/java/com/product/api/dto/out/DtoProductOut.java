package com.product.api.dto.out;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DtoProductOut {

    @Id
	@JsonProperty("product_id")
	private Integer product_id;
	
	@JsonProperty("gtin")
	private String gtin;

	@JsonProperty("product")
	private String product;

	@JsonProperty("description")
	private String description;

	@JsonProperty("price")
	private Float price;
	
	@JsonProperty("stock")
	private Integer stock;

    @JsonProperty("status")
	private Integer status;

    @JsonProperty("category")
    private String category;

    @JsonProperty("images")
    private List<String> images;
}
