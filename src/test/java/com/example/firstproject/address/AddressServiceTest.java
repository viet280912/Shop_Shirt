package com.example.firstproject.address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.Address.AddressService;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    private User user;

    private Address addressDemo;
    @BeforeEach
    public void setUp(){
        user = new User();
        user.setFullName("Ho Xuan Thuy");
        userRepository.save(user);

        addressDemo = new Address();
        addressDemo.setAddress_id(0);
        addressDemo.setCountry("TQ");
        addressDemo.setCity("BK");
        addressDemo.setDistrict("DS");
        addressDemo.setWard("WA");
        addressDemo.setStreet("ST");
        addressDemo.setUser(user);
        addressRepository.save(addressDemo);

    }

    @Test
    void createAddressReturnAddress(){
        //given


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
    @Rollback
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
        NotFoundException exception = assertThrows(NotFoundException.class, () -> addressService.createAddress(addressDTO));

        //then
        assertEquals("Not found user with id: 4", exception.getMessage());
    }
    @Test
    @Rollback
    void updateAddressTest_Success(){
        AddressDTO addressDTO = addressMapper.convertToAddressDTO(addressDemo);
        addressDTO.setCountry("VN");

        //when
        Address rs = addressService.updateAddress(addressDemo.getAddress_id(), addressDTO);

        //then
        assertEquals("VN", rs.getCountry());
    }

    @Test
    @Rollback
    void deleteOneAddressTest_Success(){

        //when
        Integer id = addressDemo.getAddress_id();
        addressService.deleteOneAddress(addressDemo.getAddress_id());

        //then
        NotFoundException exception1 = assertThrows(NotFoundException.class, () -> {
            addressRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Address not found with id: " + id));
        });


        assertEquals("Address not found with id: "+id, exception1.getMessage());
    }
}
