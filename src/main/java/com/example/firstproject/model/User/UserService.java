package com.example.firstproject.model.User;

import com.example.firstproject.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
    UserDTO updateUser(int userId, UserDTO userDto);
    void deleteUser(int userId);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int userId);
}
