package com.example.firstproject.model.Cart;

import com.example.firstproject.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends
        PagingAndSortingRepository<Cart, Integer>,
        JpaRepository<Cart, Integer>{
    Optional<Cart> findCartByUser(User user);
}
