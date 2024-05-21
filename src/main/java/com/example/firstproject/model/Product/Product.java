package com.example.firstproject.model.Product;

import com.example.firstproject.model.Category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Product {
    private int product_id;

    private String product_name;

    private String description;

    private Float price;

    private Date created_At;

    private Date update_At;


    private Category category;
}
