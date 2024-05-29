package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;

import java.util.List;

public interface ProductService {
//    get all product
    List<ProductDTO> getAll();

//    get single product by id
    ProductDTO getProductByID(int id);

//    get product in range price
    List<ProductDTO> getProductsInRangePrice (Float x, Float y);

//    search product by name, category
    List<ProductDTO> searchProductsByName(String name, String category);

//    add product
    Product createProduct (ProductDTO productDTO);

//    update product
    Product updateProduct (ProductDTO productDTO);

//    delete product
    void deleteProduct (int id);
}
