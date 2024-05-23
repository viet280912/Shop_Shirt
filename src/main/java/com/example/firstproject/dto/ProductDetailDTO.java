package com.example.firstproject.dto;

import lombok.Data;
import java.sql.Blob;

@Data
public class ProductDetailDTO {
    private int productDetailId;
    private String size;
    private String color;
    private int stock;
    private Blob image;
    private int productId;
}
