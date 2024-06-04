package com.example.firstproject.model.Product;

import com.example.firstproject.model.OrderDetail.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT products.* FROM products WHERE products.product_id = :id", nativeQuery = true)
    Product findProductByID(@Param("id") int id);

    @Query(value = "SELECT products.* FROM products", nativeQuery = true)
    Page<Product> getProductPage(Pageable pageable);

    @Query(value = "SELECT products.* FROM products WHERE products.product_id = :id", nativeQuery = true)
    Optional<Product> checkEmptyProduct (int id);

    @Query(value = "SELECT products.* FROM products \n" +
            "JOIN product_category ON product_category.product_id = products.product_id\n" +
            " JOIN categories ON categories.category_id = product_category.category_id \n" +
            " WHERE categories.name LIKE LOWER(CONCAT('%', :category, '%')) \n" +
            " AND products.product_name LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    Page<Product> searchProduct (String name, String category, Pageable pageable);

    @Query(value = "SELECT products.* FROM products JOIN product_category ON product_category.product_id = products.product_id WHERE product_category.category_id = :category_id", nativeQuery = true)
    Page<Product> getProductByCategory(int category_id, Pageable pageable);
    @Query(value = "SELECT products.* FROM products JOIN product_category ON product_category.product_id = products.product_id WHERE product_category.category_id = :category_id", nativeQuery = true)
    List<Product> getProductByCategoryNotPage(int category_id);


    @Query(value = "SELECT products.* FROM products WHERE products.price BETWEEN :price_x AND :price_y", nativeQuery = true)
    Page<Product> getProductInRangePrice(Float price_x, Float price_y, Pageable pageable);

}
