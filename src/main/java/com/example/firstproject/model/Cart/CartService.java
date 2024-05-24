package com.example.firstproject.model.Cart;

import com.example.firstproject.dto.CartItemDTO;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart findCartByUser(Integer userId);
    Cart addCartItemToCart(Integer userId, CartItemDTO cartItemDTO);
    Cart removeCartItemInCart(Integer userId, Integer cartItemId);
}
