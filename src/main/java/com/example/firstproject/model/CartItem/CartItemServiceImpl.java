package com.example.firstproject.model.CartItem;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.CartItemMapper;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartRepository;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import com.example.firstproject.model.ProductDetail.ProductDetailRepository;
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
    @Autowired
    private ProductDetailRepository productDetailRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("Not found cartItem with id: " + cartItemDTO.getId()));
        cartItemUpdating.setQuantity(cartItemDTO.getQuantity());
        cartItemRepository.save(cartItemUpdating);
        return cartItemUpdating;
    }

    @Override
    public CartItem createCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItem_id(0);
        User user = userRepository.findById(cartItemDTO.getUserId())
                        .orElseThrow(() -> new NotFoundException("User not found with id: "+ cartItemDTO.getUserId()));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new NotFoundException("Cart not found with user id: " + cartItemDTO.getUserId()));
        cartItem.setCart(cart);
        cartItem.setQuantity(cartItemDTO.getQuantity());
        ProductDetail productDetail = productDetailRepository.findById(cartItemDTO.getProductDetailId())
                        .orElseThrow(() -> new NotFoundException("Product detail not found with id: " + cartItemDTO.getProductDetailId()));
        cartItem.setProductDetail(productDetail);
        cartItemRepository.save(cartItem);
        return cartItem;
    }
}
