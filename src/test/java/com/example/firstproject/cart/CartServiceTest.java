package com.example.firstproject.cart;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.mapper.UserMapper;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartService;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import com.example.firstproject.model.User.UserService;
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
public class CartServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;
    @Test
    void findCartByUserTest(){
        //given
        User user = new User();
        user.setFullName("full name");
        user.setUserName("anhdaychap12");
        user.setPassword("12345678");
        user.setEmail("phuctxp15200@gmail.com");
        Cart cart = new Cart();
        cart.setCreated_At(Date.valueOf(LocalDate.now()));
        cart.setCartItems(new ArrayList<>());
        cart.setUser(user);
        user.setCart(cart);
        userRepository.save(user);

        //when
        Cart result = cartService.findCartByUser(user.getUser_id());

        //then
        assertEquals(user.getUser_id(), result.getUser().getUser_id());
        assertEquals(Date.valueOf(LocalDate.now()), result.getCreated_At());
    }
}
