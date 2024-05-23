package com.example.firstproject.model.CartItem;

import com.example.firstproject.dto.CartItemDTO;

import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItemByUserId(Integer userId);
    CartItem updateCartItemById(CartItemDTO cartItemDTO);
}
