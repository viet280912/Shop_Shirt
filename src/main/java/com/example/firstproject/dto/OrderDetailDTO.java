package com.example.firstproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("orderDetail_id")
    private int orderDetail_id;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("order_id")
    private int order_id;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("productDetail")
    private ProductDetailDTO productDetail;
}
