package com.example.firstproject.mapper;

import com.example.firstproject.dto.CartDTO;
import com.example.firstproject.model.Cart.Cart;

public interface CartMapper {
    Cart toCart(CartDTO cartDTO);
    CartDTO toCartDTO(Cart cart);
}
