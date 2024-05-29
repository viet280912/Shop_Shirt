package com.example.firstproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @JsonProperty("product_id")
    private int category_id;

    @JsonProperty("name")
    private String name;
}
