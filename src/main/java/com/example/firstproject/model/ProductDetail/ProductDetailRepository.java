package com.example.firstproject.model.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {

}
