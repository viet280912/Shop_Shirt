package com.example.firstproject.cartItem;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.CartItem.CartItem;
import com.example.firstproject.model.CartItem.CartItemRepository;
import com.example.firstproject.model.CartItem.CartItemService;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class CartItemServiceTest {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserRepository userRepository;
    @Test
    void createCartItemTest(){
        //given
        ProductDetail productDetail = new ProductDetail();
        User user = new User();
        user.setUser_id(0);
        user.setFullName("full name");
        user.setUserName("anhdaychap12");
        user.setPassword("12345678");
        user.setEmail("phuctxp15200@gmail.com");
        Cart cart = new Cart();
        cart.setCreated_At(Date.valueOf(LocalDate.now()));
        cart.setCartItems(new ArrayList<>());
        cart.setUser(user);
        user.setCart(cart);
        cart.setUser(user);
        userRepository.save(user);
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(0);
        cartItemDTO.setCartId(cart.getCart_id());
        cartItemDTO.setQuantity(5);

        //when
        CartItem result = cartItemService.createCartItem(cartItemDTO);
        CartItem ex = cartItemRepository.findById(result.getCartItem_id())
                .orElseThrow(() -> new EntityNotFoundException("Not found cartItem"));

        //then
        assertEquals(ex.getCart().getCart_id(), result.getCart().getCart_id());
        assertEquals(ex.getQuantity(), result.getQuantity());
    }
}
