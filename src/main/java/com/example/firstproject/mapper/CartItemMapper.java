package com.example.firstproject.mapper;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.model.CartItem.CartItem;

public interface CartItemMapper {
    CartItem convertToCartItem(CartItemDTO cartItemDTO);
    CartItemDTO convertToCartItemDTO(CartItem cartItem);
}
