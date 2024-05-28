package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.model.Order.Order;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetail_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    private int quantity;

    private Float price;

    public OrderDetail(Order order, ProductDetail productDetail, int quantity, Float price) {
        this.order = order;
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.price = price;
    }
}
