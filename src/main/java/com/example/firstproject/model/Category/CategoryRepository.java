package com.example.firstproject.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT categories.* FROM categories JOIN products ON products.category_id = categories.category_id WHERE products.product_id = :product_id", nativeQuery = true)
    List<Category> getCategoryByProductId(@Param("product_id") int product_id);

    @Query(value = "SELECT categories.* FROM categories WHERE categories.name LIKE LOWER(CONCAT('%', :category, '%'))", nativeQuery = true)
    List<Category> findCategoriesByName (@Param("category") String category);
}
