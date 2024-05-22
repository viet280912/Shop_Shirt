package com.example.firstproject.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoriesByProductID(int product_id) {
        return categoryRepository.getCategoryByProductId(product_id);
    }

    @Override
    public List<Category> findCategoriesByName(String category) {
        return categoryRepository.findCategoriesByName(category);
    }
}
