package com.example.firstproject.controller;

import com.example.firstproject.dto.CartItemDTO;
import com.example.firstproject.model.CartItem.CartItem;
import com.example.firstproject.model.CartItem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @GetMapping("user/{userId}/cart/items")
    public ResponseEntity<?> getAllItemInCartByUserId(
            @PathVariable Integer userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Page<CartItem> cartItems = cartItemService.getAllCartItemByUserId(userId, page, size);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PutMapping("cart/items/{id}")
    public ResponseEntity<?> updateItemInCart(
            @PathVariable Integer id,
            @RequestBody CartItemDTO cartItemDTO
            ){
        return new ResponseEntity<>(cartItemService.updateCartItemById(id, cartItemDTO), HttpStatus.OK);
    }
}
