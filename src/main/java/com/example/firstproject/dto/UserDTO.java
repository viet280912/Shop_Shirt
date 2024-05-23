package com.example.firstproject.dto;

import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Cart.Cart;
import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private int userId;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    public List<Address> addresses;
    public Cart cart;
}
