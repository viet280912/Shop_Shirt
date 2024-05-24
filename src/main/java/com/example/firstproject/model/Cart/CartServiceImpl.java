package com.example.firstproject.model.Cart;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.exception.ErrorCreateBecauseEntityExistInLisException;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.CartItemMapper;
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
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public Cart findCartByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+ userId));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Not found cart with userId: "+ userId));
        return cart;
    }

    @Override
    public Cart addCartItemToCart(Integer userId, CartItemDTO cartItemDTO) {
        //find cart by user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with userid: "+ userId));

        //check cartItem in list<cartItem>
        List<CartItem> cartItemListUpdating = cart.getCartItems();
        boolean checkCartItemExist = cartItemListUpdating
                .stream()
                .map(item -> item.getProductDetail().getProductDetail_id())
                .anyMatch(id -> id.equals(cartItemDTO.getProductDetailId()));
        if (checkCartItemExist){
            throw new ErrorCreateBecauseEntityExistInLisException("Product detail exist in cart.");
        }

        //creat cart item
        CartItem cartItem = new CartItem();
        CartItem cartItemMapped = cartItemMapper.convertToCartItem(cartItemDTO);
        cartItem.setCartItem_id(cartItemDTO.getId());
        cartItem.setCart(cartItemMapped.getCart());
        cartItem.setQuantity(cartItemMapped.getQuantity());
        cartItem.setProductDetail(cartItemMapped.getProductDetail());
        cartItemRepository.save(cartItem);


        //add cartItem to list
        cartItemListUpdating.add(cartItem);
        cart.setCartItems(cartItemListUpdating);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart removeCartItemInCart(Integer userId, Integer cartItemId) {
        //find cart by user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Cart cart = cartRepository.findCartByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with userid: "+ userId));

        //check cartItem in list<cartItem>
        List<CartItem> cartItemListUpdating = cart.getCartItems();
        boolean checkCartItemExist = cartItemListUpdating
                .stream()
                .map(CartItem::getCartItem_id)
                .anyMatch(id -> id.equals(cartItemId));
        if (!checkCartItemExist){
            throw new NotFoundException("Not found cartItem in cart.");
        }

        //remove cartItem in list<cartItem>
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NotFoundException("Not found cartItem with id: "+ cartItemId));
        cartItemListUpdating.remove(cartItem);
        cart.setCartItems(cartItemListUpdating);
        cartRepository.save(cart);
        return cart;
    }


}
