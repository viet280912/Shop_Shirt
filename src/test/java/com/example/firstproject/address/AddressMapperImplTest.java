package com.example.firstproject.address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.mapper.AddressMapperImpl;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressMapperImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddressMapperImpl addressMapper;

    private AddressDTO addressDTO;
    private User user;
    private Address address;

    @BeforeEach
    void setUp() {
        addressDTO = new AddressDTO();
        addressDTO.setId(1);
        addressDTO.setCountry("Country");
        addressDTO.setCity("City");
        addressDTO.setDistrict("District");
        addressDTO.setWard("Ward");
        addressDTO.setStreet("Street");
        addressDTO.setUser_id(1);

        user = new User();
        user.setUser_id(1);

        address = new Address();
        address.setAddress_id(1);
        address.setCountry("Country");
        address.setCity("City");
        address.setDistrict("District");
        address.setWard("Ward");
        address.setStreet("Street");
        address.setUser(user);
    }

    @Test
    void convertToAddress_ShouldReturnAddress_WhenUserExists() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Address result = addressMapper.convertToAddress(addressDTO);

        assertNotNull(result);
        assertEquals(1, result.getAddress_id());
        assertEquals("Country", result.getCountry());
        assertEquals("City", result.getCity());
        assertEquals("District", result.getDistrict());
        assertEquals("Ward", result.getWard());
        assertEquals("Street", result.getStreet());
        assertEquals(user, result.getUser());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void convertToAddress_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            addressMapper.convertToAddress(addressDTO);
        });

        assertEquals("Not found user with id: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void convertToAddressDTO_ShouldReturnAddressDTO() {
        AddressDTO result = addressMapper.convertToAddressDTO(address);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Country", result.getCountry());
        assertEquals("City", result.getCity());
        assertEquals("District", result.getDistrict());
        assertEquals("Ward", result.getWard());
        assertEquals("Street", result.getStreet());
        assertEquals(1, result.getUser_id());
    }
}
