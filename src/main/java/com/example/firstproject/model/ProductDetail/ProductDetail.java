package com.example.firstproject.model.ProductDetail;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;

@Data
@AllArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetail_id;

    private String size;

    private String color;

    private int stock;

    private Blob image;
}
