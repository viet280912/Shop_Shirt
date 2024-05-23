package com.example.firstproject.model.Category;

import com.example.firstproject.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories;
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<Category> getCategoriesByProductID(int product_id) {
        List<Category> categories = categoryRepository.getCategoryByProductId(product_id);
        if (!categories.isEmpty()) {
            return categories;
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<Category> findCategoriesByName(String category) {
        List<Category> categories =  categoryRepository.findCategoriesByName(category);
        if (!categories.isEmpty()) {
            return categories;
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
