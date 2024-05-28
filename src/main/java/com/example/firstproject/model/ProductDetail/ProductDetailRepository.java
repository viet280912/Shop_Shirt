package com.example.firstproject.model.ProductDetail;

import com.example.firstproject.dto.ProductDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query(value = "SELECT product_details.* FROM product_details WHERE product_details.product_detail_id = :id", nativeQuery = true)
    ProductDetail findItemByID(@Param("id") int id);
}
