package com.example.firstproject.mapper;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.User.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-31T16:05:37+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFullName( user.getFullName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setUserName( user.getUserName() );
        userDTO.setPassword( user.getPassword() );
        List<Address> list = user.getAddresses();
        if ( list != null ) {
            userDTO.setAddresses( new ArrayList<Address>( list ) );
        }
        userDTO.setCart( user.getCart() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( userDto.getFullName() );
        user.setEmail( userDto.getEmail() );
        user.setUserName( userDto.getUserName() );
        user.setPassword( userDto.getPassword() );
        List<Address> list = userDto.getAddresses();
        if ( list != null ) {
            user.setAddresses( new ArrayList<Address>( list ) );
        }
        user.setCart( userDto.getCart() );

        return user;
    }
}
