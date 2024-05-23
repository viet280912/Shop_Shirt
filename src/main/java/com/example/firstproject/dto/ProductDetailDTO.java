package com.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private int productDetailId;
    private String size;
    private String color;
    private int stock;
    private Blob image;
    private int productId;
}
