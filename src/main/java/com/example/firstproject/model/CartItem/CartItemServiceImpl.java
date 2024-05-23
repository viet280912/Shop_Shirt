package com.example.firstproject.model.CartItem;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.mapper.CartItemMapper;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartRepository;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Override
    public List<CartItem> getAllCartItemByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+ userId));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Not found cart with userId: "+ userId));
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        if (cartItems.isEmpty()){
            throw new RuntimeException("List cartItem is empty.");
        }
        return cartItems;
    }

    @Override
    public CartItem updateCartItemById(CartItemDTO cartItemDTO) {
        CartItem cartItemUpdating = cartItemRepository.findById(cartItemDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found cartitem with id: " + cartItemDTO.getId()));
        CartItem cartItemMapped = cartItemMapper.convertToCartItem(cartItemDTO);
        cartItemUpdating.setQuantity(cartItemMapped.getQuantity());
        cartItemRepository.save(cartItemUpdating);
        return cartItemUpdating;
    }
}
