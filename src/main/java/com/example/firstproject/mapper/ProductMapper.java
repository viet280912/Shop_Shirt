package com.example.firstproject.mapper;

import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.model.Product.Product;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper implements Function<Product, ProductDTO> {
    @Autowired
    private ProductDetailToDTO productDetailToDTO;
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getProduct_id(),
                product.getProduct_name(),
                product.getDescription(),
                product.getPrice(),
                product.getCreated_At(),
                product.getUpdate_At(),
                product.getCategories(),
                product.getProductDetails().stream().map(productDetailToDTO).collect(Collectors.toList())
        );
    }
    public Product convertProduct (ProductDTO productDTO) {
        return new Product(
                productDTO.getProduct_id(),
                productDTO.getProduct_name(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getCreated_At(),
                productDTO.getUpdate_At(),
                productDTO.getCategories(),
                productDTO.getDetails().stream().map(productDetailToDTO::convertToProductDetail).collect(Collectors.toList())
        );
    }
}
