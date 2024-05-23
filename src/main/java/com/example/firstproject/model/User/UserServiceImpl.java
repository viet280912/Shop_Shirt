package com.example.firstproject.model.User;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.mapper.UserMapper;
import com.example.firstproject.model.Cart.Cart;
import com.example.firstproject.model.Cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        User userCreating = new User();
        //khi mình tạo mới người dùng thì giỏ hàng cũng được tạo luôn
        Cart cart = new Cart();
        cart.setCreated_At(Date.valueOf(LocalDate.now()));
        cart.setUser(user);
        cart.setCartItems(new ArrayList<>());
        cart.setUser(userCreating);
        userCreating.setUser_id(0);
        userCreating.setCart(cart);
        userCreating.setAddresses(new ArrayList<>());
        userCreating.setFullName(user.getFullName());
        userCreating.setEmail(user.getEmail());
        userCreating.setUserName(user.getUserName());
        userCreating.setPassword(user.getPassword());
        userCreating.setCart(cart);
        userRepository.save(userCreating);
        return UserMapper.INSTANCE.toDto(userCreating);
    }

    @Override
    public UserDTO updateUser(int userId, UserDTO userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFullName(userDto.getFullName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setUserName(userDto.getUserName());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setAddresses(userDto.getAddresses());
        existingUser.setCart(userDto.getCart());

        existingUser = userRepository.save(existingUser);
        return UserMapper.INSTANCE.toDto(existingUser);
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.toDto(user);
    }
}
