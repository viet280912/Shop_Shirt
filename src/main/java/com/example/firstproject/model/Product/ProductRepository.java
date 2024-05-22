package com.example.firstproject.model.Product;

import com.example.firstproject.model.OrderDetail.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT products.* FROM products WHERE products.product_id = :id", nativeQuery = true)
    Product findProductByID(@Param("id") int id);

    @Query(value = "SELECT products.* FROM products WHERE products.product_id = :id", nativeQuery = true)
    Optional<Product> checkEmptyProduct (int id);
}
