package com.example.firstproject.mapper;

import com.example.firstproject.dto.CartDTO;
import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.model.Cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartMapperImpl implements CartMapper{
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public Cart toCart(CartDTO cartDTO) {
        return null;
    }

    @Override
    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getCart_id());
        cartDTO.setCreated_At(cart.getCreated_At());
        cartDTO.setUserId(cart.getUser().getUser_id());
        cartDTO.setCartItemDTOS(
                cart.getCartItems()
                        .stream()
                        .map(cartItem -> cartItemMapper.convertToCartItemDTO(cartItem))
                        .collect(Collectors.toList())
        );
        return cartDTO;
    }
}
