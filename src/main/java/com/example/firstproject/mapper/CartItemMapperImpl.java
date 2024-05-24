package com.example.firstproject.mapper;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartRepository;
import com.example.firstproject.model.CartItem.CartItem;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import com.example.firstproject.model.ProductDetail.ProductDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapperImpl implements CartItemMapper{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public CartItem convertToCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItem_id(cartItemDTO.getId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        ProductDetail productDetail = productDetailRepository.findById(cartItemDTO.getProductDetailId())
                .orElseThrow(() -> new NotFoundException("Product detail not found with id: "+ cartItemDTO.getProductDetailId()));
        cartItem.setProductDetail(productDetail);
        Cart cart = cartRepository.findById(cartItemDTO.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Not found cart with id: " + cartItemDTO.getCartId()));
        cartItem.setCart(cart);
        return cartItem;
    }

    @Override
    public CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        return null;
    }
}
