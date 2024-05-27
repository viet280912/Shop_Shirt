package com.example.firstproject.controller;

import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("user/{userId}/cart")
    public ResponseEntity<?> getCartByUser(
            @PathVariable Integer userId
    ){
        Cart cart = cartService.findCartByUser(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


}
