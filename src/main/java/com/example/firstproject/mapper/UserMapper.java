package com.example.firstproject.mapper;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.model.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);

    User toEntity(UserDTO userDto);
}
