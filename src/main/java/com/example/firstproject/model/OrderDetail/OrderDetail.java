package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.model.Order.Order;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetail_id;

    private Order order;

    private ProductDetail productDetail;

    private int quantity;

    private Float price;
}
