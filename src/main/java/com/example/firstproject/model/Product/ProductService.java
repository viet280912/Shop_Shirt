package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;

import java.util.List;

public interface ProductService {
//    get all product
    List<ProductDTO> getAll();

//    get single product by id
    ProductDTO getProductByID(int id);

//
}
