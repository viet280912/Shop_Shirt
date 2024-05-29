package com.example.firstproject.dto;

import com.example.firstproject.model.Category.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @JsonProperty("product_id")
    private int product_id;

    @JsonProperty("product_name")
    private String product_name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("created_At")
    private Date created_At;

    @JsonProperty("update_At")
    private Date update_At;

    @JsonProperty("categories")
    private List<CategoryDTO> categories;

    @JsonProperty("details")
    private List<ProductDetailDTO> details;
}
