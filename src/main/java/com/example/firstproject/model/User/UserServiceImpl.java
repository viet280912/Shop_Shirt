package com.example.firstproject.model.User;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.toDto(user);
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
        existingUser.setCarts(userDto.getCarts());

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
