package com.example.firstproject.mapper;

import com.example.firstproject.dto.CategoryDTO;
import com.example.firstproject.model.Category.Category;
import com.example.firstproject.model.Product.Product;
import com.example.firstproject.model.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryToDTO implements Function<Category, CategoryDTO> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getCategory_id(),
                category.getName()
        );
    }
    public Category convert (int product_id, CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getCategory_id(),
                categoryDTO.getName(),
                productRepository.getProductByCategoryNotPage(categoryDTO.getCategory_id())
        );
    }
}
