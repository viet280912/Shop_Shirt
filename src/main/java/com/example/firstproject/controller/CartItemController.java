package com.example.firstproject.controller;

import com.example.firstproject.model.CartItem.CartItem;
import com.example.firstproject.model.CartItem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
//    @GetMapping("user/{userId}/cart/items")
//    public ResponseEntity<?> getAllItemInCartByUserId(
//            @PathVariable Integer userId
//    ){
////        Page<CartItem> cartItems = cartItemService.getAllCartItemByUserId(userId);
//    }
}
