package com.example.firstproject.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart findCartByUser(Integer userId);
}
