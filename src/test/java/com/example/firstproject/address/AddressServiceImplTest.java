package com.example.firstproject.address;

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

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private User user;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setCountry("Country");
        address.setCity("City");
        address.setDistrict("District");
        address.setWard("Ward");
        address.setStreet("Street");

        user = new User();
        user.setUser_id(1);
    }

    @Test
    void createAddress_ShouldReturnAddress_WhenUserExists() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address createdAddress = addressService.createAddress(address, 1);

        assertNotNull(createdAddress);
        assertEquals("Country", createdAddress.getCountry());
        assertEquals("City", createdAddress.getCity());
        assertEquals("District", createdAddress.getDistrict());
        assertEquals("Ward", createdAddress.getWard());
        assertEquals("Street", createdAddress.getStreet());
        verify(userRepository, times(1)).findById(1);
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void createAddress_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            addressService.createAddress(address, 1);
        });

        assertEquals("Not found user with id1", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
        verify(addressRepository, times(0)).save(any(Address.class));
    }
}
