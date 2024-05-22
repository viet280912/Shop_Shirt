package com.example.firstproject.model.Address;

import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user with id" + userId));
        Address addressCreating = new Address();
        addressCreating.setAddress_id(0);
        addressCreating.setCountry(address.getCountry());
        addressCreating.setCity(address.getCity());
        addressCreating.setDistrict(address.getDistrict());
        addressCreating.setWard(address.getWard());
        addressCreating.setStreet(address.getStreet());
        addressCreating.setUser(user);
        Address addressCreated = addressRepository.save(addressCreating);
        return addressCreated;
    }


}
