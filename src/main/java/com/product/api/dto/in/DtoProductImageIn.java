package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
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
public class DtoProductImageIn {

    @NotNull(message = "El productId es obligatorio")
    @JsonProperty("product_id")
    private Integer product_id;

    @NotNull(message = "El image es obligatorio")
    @JsonProperty("image")
    private String image;
}
