package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class DtoProductImageIn {

    @JsonProperty("product_id")
    @NotNull(message = "El productId es obligatorio")
    private Integer product_Id;

    @JsonProperty("image")
    @NotNull(message = "El image es obligatorio")
    private String image;

    public Integer getProduct_id() {
        return product_Id;
    }

    public void setProduct_id(Integer product_Id) {
        this.product_Id = product_Id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
