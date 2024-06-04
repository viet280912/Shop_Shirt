package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;

import java.util.List;

public interface ProductService {
//    get all product
    DataProduct getAll(int page);

//    get single product by id
    ProductDTO getProductByID(int id);

//    get product in range price
    DataProduct getProductsInRangePrice (Float x, Float y, int page);

//    search product by name, category
    List<ProductDTO> searchProductsByName(String name, String category, int page);

//    add product
    Product createProduct (ProductDTO productDTO);

//    update product
    Product updateProduct (ProductDTO productDTO);

//    delete product
    void deleteProduct (int id);
}
