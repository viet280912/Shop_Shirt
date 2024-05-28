package com.example.firstproject.address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    private User user;
    private Address address1, address2, address3;
    @BeforeEach
    public void setUp(){
        user = new User();
        user.setFullName("Tran Xuan phuc");
        userRepository.save(user);

        address1 = new Address();
        address1.setUser(user);
        address1.setCountry("Country1");
        address1.setCity("City1");
        address1.setDistrict("Dis1");
        address1.setStreet("Street1");
        address1.setWard("Ward1");
        addressRepository.save(address1);

        address2 = new Address();
        address2.setUser(user);
        address2.setCountry("Country2");
        address2.setCity("City2");
        address2.setDistrict("Dis2");
        address2.setStreet("Street2");
        address2.setWard("Ward2");
        addressRepository.save(address2);

        address3 = new Address();
        address3.setUser(user);
        address3.setCountry("Country3");
        address3.setCity("City3");
        address3.setDistrict("Dis3");
        address3.setStreet("Street3");
        address3.setWard("Ward3");
        addressRepository.save(address3);
    }
    @Test
    void getAllAddressByUserIdTest() throws Exception {
        mockMvc.perform(get("/api/addresses/user/"+user.getUser_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @Rollback
    void getAllAddressByUserIdTest_ThrowException() throws Exception {
        userRepository.deleteAll();
        mockMvc.perform(get("/api/addresses/user/"+user.getUser_id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    @Test
    @Rollback
    void createAddressTest_Success() throws Exception{
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUser_id(user.getUser_id());
        addressDTO.setCountry("VN");
        addressDTO.setCity("HN");
        addressDTO.setDistrict("GL");
        addressDTO.setWard("KK");
        addressDTO.setStreet("BĐ");
        addressDTO.setId(0);
        ObjectMapper objectMapper = new ObjectMapper();


        mockMvc.perform(post("/api/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.country", is("VN")));

    }

    @Test
    @Rollback
    void updateAddressTest_Success() throws Exception {
        AddressDTO addressDTO = addressMapper.convertToAddressDTO(address1);
        addressDTO.setCountry("VN");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/addresses/"+address1.getAddress_id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", is("VN")));
    }
}
