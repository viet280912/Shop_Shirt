//package com.example.firstproject.cart;
//
//import com.example.firstproject.dto.CartItemDTO;
//import com.example.firstproject.model.Cart.Cart;
//import com.example.firstproject.model.Cart.CartRepository;
//import com.example.firstproject.model.CartItem.CartItem;
//import com.example.firstproject.model.CartItem.CartItemRepository;
//import com.example.firstproject.model.Product.Product;
//import com.example.firstproject.model.Product.ProductRepository;
//import com.example.firstproject.model.ProductDetail.ProductDetail;
//import com.example.firstproject.model.ProductDetail.ProductDetailRepository;
//import com.example.firstproject.model.User.User;
//import com.example.firstproject.model.User.UserRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional
//public class CartControllerTest {
//
//    @Autowired
//    private CartRepository cartRepository;
//    @Autowired
//    private CartItemRepository cartItemRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private ProductDetailRepository productDetailRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private User user;
//    private Cart cart1;
//
//    private Product product1, product2, product3;
//
//    private ProductDetail productDetail1, productDetail2, productDetail3, productDetail4, productDetail5, productDetail6;
//
//    @BeforeEach
//    public void setUp() {
//        user = new User();
//        user.setFullName("LE Duc Anh");
//
//        cart1 = new Cart();
//        cart1.setCreated_At(Date.valueOf(LocalDate.now()));
//        cart1.setCartItems(new ArrayList<>());
//        cart1.setUser(user);
//        user.setCart(cart1);
//        userRepository.save(user);
//
//        //given
//        product1 = new Product();
//        product1.setProduct_name("product1");
//        productRepository.save(product1);
//        product2 = new Product();
//        product2.setProduct_name("product2");
//        productRepository.save(product2);
//        product3 = new Product();
//        product3.setProduct_name("product3");
//        productRepository.save(product3);
//
//        productDetail1 = new ProductDetail();
//        productDetail1.setProduct(product1);
//        productDetail1.setColor("#Color1");
//        productDetail1.setSize("M");
//        productDetail1.setStock(20);
//        productDetailRepository.save(productDetail1);
//
//        productDetail2 = new ProductDetail();
//        productDetail2.setProduct(product1);
//        productDetail2.setColor("#Color1");
//        productDetail2.setSize("L");
//        productDetail2.setStock(20);
//        productDetailRepository.save(productDetail2);
//
//        productDetail3 = new ProductDetail();
//        productDetail3.setProduct(product1);
//        productDetail3.setColor("#Color1");
//        productDetail3.setSize("XL");
//        productDetail3.setStock(20);
//        productDetailRepository.save(productDetail3);
//
//        productDetail4 = new ProductDetail();
//        productDetail4.setProduct(product2);
//        productDetail4.setColor("#Color1");
//        productDetail4.setSize("XL");
//        productDetail4.setStock(20);
//        productDetailRepository.save(productDetail4);
//
//        productDetail5 = new ProductDetail();
//        productDetail5.setProduct(product2);
//        productDetail5.setColor("#Color1");
//        productDetail5.setSize("XL");
//        productDetail5.setStock(20);
//        productDetailRepository.save(productDetail5);
//
//        productDetail6 = new ProductDetail();
//        productDetail6.setProduct(product2);
//        productDetail6.setColor("#Color1");
//        productDetail6.setSize("XL");
//        productDetail6.setStock(20);
//        productDetailRepository.save(productDetail6);
//
//    }
//
//    @Test
//    void getCartByUserId_Success() throws Exception {
//        mockMvc.perform(get("/api/user/"+user.getUser_id()+"/cart")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.created_At", is(String.valueOf(LocalDate.now()))));
//
//    }
//    @Test
//    @Rollback
//    void addItemToCart_Success() throws Exception {
//        CartItemDTO cartItemDTO = new CartItemDTO();
//        cartItemDTO.setProductDetailId(productDetail1.getProductDetail_id());
//        cartItemDTO.setQuantity(4);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(put("/api/user/"+user.getUser_id()+"/cart/addItem")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(cartItemDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.cartItemDTOS.size()", is(1)));
//    }
//    @Test
//    @Rollback
//    void addItemToCart_FailBecauseItemExistInCart() throws Exception {
//        CartItem cartItem2 = new CartItem();
//        cartItem2.setProductDetail(productDetail1);
//        cartItem2.setQuantity(5);
//        cartItem2.setCart(cart1);
//        cart1.getCartItems().add(cartItem2);
//        cartItemRepository.save(cartItem2);
//        cartRepository.save(cart1);
//        CartItemDTO cartItemDTO = new CartItemDTO();
//        cartItemDTO.setProductDetailId(productDetail1.getProductDetail_id());
//        cartItemDTO.setQuantity(4);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(put("/api/user/"+user.getUser_id()+"/cart/addItem")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(cartItemDTO)))
//                .andExpect(status().isConflict());
//    }
//    @Test
//    @Rollback
//    void removeOneItemInCart_Success() throws Exception {
//        //given
//        CartItem cartItem = new CartItem();
//        cartItem.setProductDetail(productDetail2);
//        cartItem.setQuantity(4);
//        cartItem.setCart(cart1);
//        cart1.getCartItems().add(cartItem);
//        cartItemRepository.save(cartItem);
//        cartRepository.save(cart1);
//
//        //when
//        mockMvc.perform(put("/api/user/"+user.getUser_id()+"/cart/removeItem/"+cartItem.getCartItem_id())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.cartItemDTOS.size()", is(0)));
//    }
//    @Test
//    @Rollback
//    void removeManyItemInCart_Success() throws Exception {
//        //given
//        CartItem cartItem1 = new CartItem();
//        cartItem1.setProductDetail(productDetail2);
//        cartItem1.setQuantity(4);
//        cartItem1.setCart(cart1);
//        cart1.getCartItems().add(cartItem1);
//        cartItemRepository.save(cartItem1);
//        cartRepository.save(cart1);
//
//        CartItem cartItem2 = new CartItem();
//        cartItem2.setProductDetail(productDetail1);
//        cartItem2.setQuantity(5);
//        cartItem2.setCart(cart1);
//        cart1.getCartItems().add(cartItem2);
//        cartItemRepository.save(cartItem2);
//        cartRepository.save(cart1);
//
//        CartItem cartItem3 = new CartItem();
//        cartItem3.setProductDetail(productDetail4);
//        cartItem3.setQuantity(5);
//        cartItem3.setCart(cart1);
//        cart1.getCartItems().add(cartItem3);
//        cartItemRepository.save(cartItem3);
//        cartRepository.save(cart1);
//
//        List<Integer> listId = new ArrayList<>();
//        listId.add(cartItem1.getCartItem_id());
//        listId.add(cartItem2.getCartItem_id());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        //when
//        mockMvc.perform(put("/api/user/"+user.getUser_id()+"/cart/removeItem")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(listId)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.cartItemDTOS.size()", is(1)));
//    }
//
//}
