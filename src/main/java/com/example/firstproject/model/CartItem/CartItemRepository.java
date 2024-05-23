package com.example.firstproject.model.CartItem;

import com.example.firstproject.model.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCart(Cart cart);
}
