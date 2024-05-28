package com.example.firstproject.address;

import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AddressRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Test
    void findAddressByUser(){
        //given
        User user = new User();
        user.setFullName("Tran Xuan Phuc");
        userRepository.save(user);

        Address address1 = new Address();
        address1.setCountry("Country1");
        address1.setCity("City1");
        address1.setDistrict("Dis1");
        address1.setWard("Ward1");
        address1.setStreet("Street1");
        address1.setUser(user);
        addressRepository.save(address1);

        Address address2 = new Address();
        address2.setCountry("Country2");
        address2.setCity("City2");
        address2.setDistrict("Dis2");
        address2.setWard("Ward2");
        address2.setStreet("Street2");
        address2.setUser(user);
        addressRepository.save(address2);

        Address address3 = new Address();
        address3.setCountry("Country3");
        address3.setCity("City3");
        address3.setDistrict("Dis3");
        address3.setWard("Ward3");
        address3.setStreet("Street3");
        address3.setUser(user);
        addressRepository.save(address3);

        //when
        List<Address> result = addressRepository.findAllByUser(user);

        //then
        assertEquals(3, result.size());
    }
}
