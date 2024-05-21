package com.example.firstproject.model.CartItem;

import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItem_id;

    private int quantity;

    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
