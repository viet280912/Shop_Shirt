package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.model.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT order_details.* FROM order_details WHERE order_details.order_detail_id = :id", nativeQuery = true)
    OrderDetail getOrderDetailByID(@Param("id") int id);

    @Query(value = "SELECT order_details.* FROM order_details WHERE order_details.order_id = :id", nativeQuery = true)
    List<OrderDetail> getOrderDetailByOrderID(@Param("id") int id);

    @Query(value = "SELECT order_details.* FROM order_details WHERE order_details.product_detail_id= :id", nativeQuery = true)
    List<OrderDetail> getOrderDetailByProductDetailID(@Param("id") int id);
}
