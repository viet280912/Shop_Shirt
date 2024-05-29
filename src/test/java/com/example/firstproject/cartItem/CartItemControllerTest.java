package com.example.firstproject.cartItem;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.CartItem.CartItem;
import com.example.firstproject.model.CartItem.CartItemRepository;
import com.example.firstproject.model.CartItem.CartItemService;
import com.example.firstproject.model.Product.Product;
import com.example.firstproject.model.Product.ProductRepository;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import com.example.firstproject.model.ProductDetail.ProductDetailRepository;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class CartItemControllerTest {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private MockMvc mockMvc;

    private User user;
    private Cart cart;
    private Product product1, product2, product3;
    private ProductDetail productDetail1, productDetail2, productDetail3, productDetail4, productDetail5, productDetail6;
    @BeforeEach
    public void setUp(){
        user = new User();
        user.setFullName("full name");
        user.setUserName("anhdaychap12");
        user.setPassword("12345678");
        user.setEmail("phuctxp15200@gmail.com");
        cart = new Cart();
        cart.setCreated_At(Date.valueOf(LocalDate.now()));
        cart.setCartItems(new ArrayList<>());
        cart.setUser(user);
        user.setCart(cart);
        userRepository.save(user);

        //given
        product1 = new Product();
        product1.setProduct_name("product1");
        productRepository.save(product1);
        product2 = new Product();
        product2.setProduct_name("product2");
        productRepository.save(product2);
        product3 = new Product();
        product3.setProduct_name("product3");
        productRepository.save(product3);

        productDetail1 = new ProductDetail();
        productDetail1.setProduct(product1);
        productDetail1.setColor("#Color1");
        productDetail1.setSize("M");
        productDetail1.setStock(20);
        productDetailRepository.save(productDetail1);

        productDetail2 = new ProductDetail();
        productDetail2.setProduct(product1);
        productDetail2.setColor("#Color1");
        productDetail2.setSize("L");
        productDetail2.setStock(20);
        productDetailRepository.save(productDetail2);

        productDetail3 = new ProductDetail();
        productDetail3.setProduct(product1);
        productDetail3.setColor("#Color1");
        productDetail3.setSize("XL");
        productDetail3.setStock(20);
        productDetailRepository.save(productDetail3);

        productDetail4 = new ProductDetail();
        productDetail4.setProduct(product2);
        productDetail4.setColor("#Color1");
        productDetail4.setSize("XL");
        productDetail4.setStock(20);
        productDetailRepository.save(productDetail4);

        productDetail5 = new ProductDetail();
        productDetail5.setProduct(product2);
        productDetail5.setColor("#Color1");
        productDetail5.setSize("XL");
        productDetail5.setStock(20);
        productDetailRepository.save(productDetail5);

        productDetail6 = new ProductDetail();
        productDetail6.setProduct(product2);
        productDetail6.setColor("#Color1");
        productDetail6.setSize("XL");
        productDetail6.setStock(20);
        productDetailRepository.save(productDetail6);
    }
    @Test
    @Rollback
    void getAllCartItemByUserId() throws Exception {
        CartItemDTO cartItemDTO1 = new CartItemDTO();
        cartItemDTO1.setUserId(user.getUser_id());
        cartItemDTO1.setQuantity(1);
        cartItemDTO1.setProductDetailId(productDetail1.getProductDetail_id());
        CartItem cartItem1 = cartItemService.createCartItem(cartItemDTO1);

        CartItemDTO cartItemDTO2 = new CartItemDTO();
        cartItemDTO2.setUserId(user.getUser_id());
        cartItemDTO2.setQuantity(3);
        cartItemDTO2.setProductDetailId(productDetail2.getProductDetail_id());
        CartItem cartItem2 = cartItemService.createCartItem(cartItemDTO2);

        CartItemDTO cartItemDTO3 = new CartItemDTO();
        cartItemDTO3.setUserId(user.getUser_id());
        cartItemDTO3.setQuantity(3);
        cartItemDTO3.setProductDetailId(productDetail3.getProductDetail_id());
        CartItem cartItem3 = cartItemService.createCartItem(cartItemDTO3);

        //then
        mockMvc.perform(get("/api/user/"+user.getUser_id()+"/cart/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(3)));
    }
    @Test
    @Rollback
    void updateCartItemById_Success() throws Exception{
        CartItemDTO cartItemDTO1 = new CartItemDTO();
        cartItemDTO1.setUserId(user.getUser_id());
        cartItemDTO1.setQuantity(1);
        cartItemDTO1.setProductDetailId(productDetail1.getProductDetail_id());
        CartItem cartItem1 = cartItemService.createCartItem(cartItemDTO1);

        CartItemDTO cartItemDTO2 = new CartItemDTO();
        cartItemDTO2.setUserId(user.getUser_id());
        cartItemDTO2.setQuantity(3);
        cartItemDTO2.setProductDetailId(productDetail2.getProductDetail_id());
        CartItem cartItem2 = cartItemService.createCartItem(cartItemDTO2);

        CartItemDTO cartItemDTO3 = new CartItemDTO();
        cartItemDTO3.setUserId(user.getUser_id());
        cartItemDTO3.setQuantity(3);
        cartItemDTO3.setProductDetailId(productDetail3.getProductDetail_id());
        CartItem cartItem3 = cartItemService.createCartItem(cartItemDTO3);

        cartItemDTO1.setQuantity(3);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/cart/items/"+cartItem1.getCartItem_id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItem3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", is(3)));

    }
}
