package com.example.firstproject.model.CartItem;

import com.example.firstproject.dto.CartItemDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartItemService {
    Page<CartItem> getAllCartItemByUserId(Integer userId, Integer pageNo, Integer pageSize);
    CartItem updateCartItemById(Integer itemId, CartItemDTO cartItemDTO);
    CartItem createCartItem(CartItemDTO cartItemDTO);
}
