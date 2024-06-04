package com.example.firstproject.model.CartItem;

import com.example.firstproject.model.Cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemRepository extends
        JpaRepository<CartItem, Integer> ,
        PagingAndSortingRepository<CartItem, Integer> {
    Page<CartItem> findAllByCart(Cart cart, Pageable pageable);
}
