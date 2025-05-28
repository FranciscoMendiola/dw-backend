package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DtoProductIn {

	@NotNull(message = "El gtin es obligatorio")
	@Pattern(regexp = "^\\+?\\d{13}$", message = "El gtin tiene un formato inválido")
	@JsonProperty("gtin")
	private String gtin;

	@NotNull(message="El product es obligatorio")
	@JsonProperty("product")
	private String product;

	@NotNull(message="El description es obligatorio")
	@JsonProperty("description")
	private String description;

	@NotNull(message="El price es obligatorio")
	@Min(value = 0, message = "El precio no puede ser negativo")
	@JsonProperty("price")
	private Double price;

	@NotNull(message="El stock es obligatorio")
	@Min(value = 0, message = "El stock no puede ser negativo")
	@JsonProperty("stock")
	private Integer stock;

	@NotNull(message = "El categoryId es obligatorio")
	@JsonProperty("categoryId")
	private Integer categoryId;
}
