package com.example.firstproject.controller;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.mapper.CartMapper;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @GetMapping("user/{userId}/cart")
    public ResponseEntity<?> getCartByUser(
            @PathVariable Integer userId
    ){
        Cart cart = cartService.findCartByUser(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("user/{userId}/cart/addItem")
    public ResponseEntity<?> addItemToCart(
            @PathVariable Integer userId,
            @RequestBody CartItemDTO cartItemDTO
            ){
        Cart cart = cartService.addCartItemToCart(userId, cartItemDTO);
        return new ResponseEntity<>(cartMapper.toCartDTO(cart), HttpStatus.OK);
    }
    @PutMapping("user/{userId}/cart/removeItem/{itemId}")
    public ResponseEntity<?> removeOneItemInCart(
            @PathVariable Integer userId,
            @PathVariable Integer itemId
    ){
        Cart cart = cartService.removeCartItemInCart(userId, itemId);
        return  new ResponseEntity<>(cartMapper.toCartDTO(cart), HttpStatus.OK);
    }
    @PutMapping("user/{userId}/cart/removeItem")
    public ResponseEntity<?> removeManyItemInCart(
            @PathVariable Integer userId,
            @RequestBody List<Integer> listItemId
    ){
        Cart cart = cartService.removeManyCartItemInCart(userId, listItemId);
        return new ResponseEntity<>(cartMapper.toCartDTO(cart), HttpStatus.OK);
    }
}
