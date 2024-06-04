package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataProduct {
    @JsonProperty("number_records")
    private Integer numberRecords;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<ProductDTO> productDTOList;
}