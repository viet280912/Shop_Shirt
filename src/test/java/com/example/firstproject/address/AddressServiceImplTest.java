package com.example.firstproject.address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.Address.AddressServiceImpl;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    private AddressDTO addressDTO;
    private Address address;
    private User user;

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
    void createAddress_ShouldReturnAddress_WhenValidDTO() {
        when(addressMapper.convertToAddress(addressDTO)).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address result = addressService.createAddress(addressDTO);

        assertNotNull(result);
        assertEquals("Country", result.getCountry());
        assertEquals("City", result.getCity());
        assertEquals("District", result.getDistrict());
        assertEquals("Ward", result.getWard());
        assertEquals("Street", result.getStreet());
        assertEquals(user, result.getUser());
        verify(addressMapper, times(1)).convertToAddress(addressDTO);
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void createAddress_ShouldThrowException_WhenUserDoesNotExist() {
        when(addressMapper.convertToAddress(addressDTO)).thenThrow(new EntityNotFoundException("Not found user with id: " + addressDTO.getUser_id()));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            addressService.createAddress(addressDTO);
        });

        assertEquals("Not found user with id: 1", exception.getMessage());
        verify(addressMapper, times(1)).convertToAddress(addressDTO);
        verify(addressRepository, times(0)).save(any(Address.class));
    }

    @Test
    void getAllAddress_ShouldReturnAllAddresses() {
        List<Address> addresses = List.of(address, new Address());
        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAllAddress();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(addressRepository, times(1)).findAll();
    }
}

