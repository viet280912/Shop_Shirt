package com.example.firstproject.model.Category;

import java.util.List;

public interface CategoryService {
//    get all category
    List<Category> getAll();

//    get categories by product id
    List<Category> getCategoriesByProductID(int product_id);

//    find categories by name
    List<Category> findCategoriesByName(String category);

//    add category
    Category createCategory (Category category);

//    update category

    Category updateCategory (Category category);

//    delete category
    void deleteCategory (int id);
}
