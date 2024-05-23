package com.example.firstproject.model.Address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Address addressCreating = new Address();
        Address addressMapped = addressMapper.convertToAddress(addressDTO);
        addressCreating.setAddress_id(0);
        addressCreating.setCountry(addressMapped.getCountry());
        addressCreating.setCity(addressMapped.getCity());
        addressCreating.setDistrict(addressMapped.getDistrict());
        addressCreating.setWard(addressMapped.getWard());
        addressCreating.setStreet(addressMapped.getStreet());
        addressCreating.setUser(addressMapped.getUser());
        addressCreating.setOrders(new ArrayList<>());
        Address addressCreated = addressRepository.save(addressCreating);
        return addressCreated;
    }


}
