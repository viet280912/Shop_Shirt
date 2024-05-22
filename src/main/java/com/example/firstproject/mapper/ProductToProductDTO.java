package com.example.firstproject.mapper;

import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.model.Product.Product;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class ProductToProductDTO implements Function<Product, ProductDTO> {
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
                null
        );
    }
}
