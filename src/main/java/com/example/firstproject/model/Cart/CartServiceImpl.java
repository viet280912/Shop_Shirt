package com.example.firstproject.model.Cart;

import com.example.firstproject.model.CartItem.CartItem;
import com.example.firstproject.model.CartItem.CartItemRepository;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart findCartByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+ userId));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Not found cart with userId: "+ userId));
        return cart;
    }

    @Override
    public Cart addCartItemToCart(Integer userId, Integer cartItemId) {
        //find cart by user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with userid: "+ userId));

        //find cartItem by id
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found with cartItemId: "+ cartItemId));

        return null;
    }
}
