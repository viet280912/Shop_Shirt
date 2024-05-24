package com.example.firstproject.address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.Address.AddressService;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void createAddressReturnAddress(){
        //given
        User user = new User();
        user.setFullName("Ho Xuan Thuy");
        userRepository.save(user);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry("Country1");
        addressDTO.setCity("City1");
        addressDTO.setDistrict("Dis1");
        addressDTO.setWard("Ward1");
        addressDTO.setStreet("Street1");
        addressDTO.setUser_id(user.getUser_id());

        //when
        Address result = addressService.createAddress(addressDTO);

        //then
        assertEquals("Country1", result.getCountry());
        assertEquals("City1", result.getCity());
        assertEquals("Dis1", result.getDistrict());
        assertEquals("Ward1", result.getWard());
        assertEquals("Street1", result.getStreet());
        assertEquals(user.getUser_id(), result.getUser().getUser_id());
    }
    @Test
    void createAddressThrowEntityNotFoundException(){
        //given
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry("Country1");
        addressDTO.setCity("City1");
        addressDTO.setDistrict("Dis1");
        addressDTO.setWard("Ward1");
        addressDTO.setStreet("Street1");
        addressDTO.setUser_id(4);

        //when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> addressService.createAddress(addressDTO));

        //then
        assertEquals("Not found user with id: 4", exception.getMessage());
    }
}
