package com.example.firstproject.mapper;

import com.example.firstproject.dto.CategoryDTO;
import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.model.Category.Category;
import com.example.firstproject.model.Product.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper implements Function<Product, ProductDTO> {
    @Autowired
    private ProductDetailToDTO productDetailToDTO;
    @Autowired
    private CategoryToDTO categoryToDTO;
    @Override
    public ProductDTO apply(Product product) {
        List<CategoryDTO> categoryDTOList = product.getCategories().stream().map(categoryToDTO).toList();
        return new ProductDTO(
                product.getProduct_id(),
                product.getProduct_name(),
                product.getDescription(),
                product.getPrice(),
                product.getCreated_At(),
                product.getUpdate_At(),
                categoryDTOList,
                product.getProductDetails().stream().map(productDetailToDTO).collect(Collectors.toList())
        );
    }
    public Product convertProduct (ProductDTO productDTO) {
        List<Category> categories1 = productDTO.getCategories()
                .stream()
                .map(categoryDTO -> categoryToDTO.convert(
                        productDTO.getProduct_id(),
                        categoryDTO)).toList();

        return new Product(
                productDTO.getProduct_id(),
                productDTO.getProduct_name(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getCreated_At(),
                productDTO.getUpdate_At(),
                categories1,
                productDTO.getDetails().stream().map(productDetailToDTO::convertToProductDetail).collect(Collectors.toList())
        );
    }
}
