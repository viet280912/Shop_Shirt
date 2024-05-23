package com.example.firstproject.model.Address;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.exception.NotFoundException;
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

    @Override
    public List<Address> getAllAddressByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+ userId));
        List<Address> addresses = addressRepository.findAllByUser(user);
        if (addresses.isEmpty()){
            throw new NotFoundException("List address is empty.");
        }
        return addresses;
    }

    @Override
    public Address updateAddress(AddressDTO addressDTO) {
        Address addressUpdating = addressRepository.findById(addressDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: "+ addressDTO.getId()));
        Address addressMapped = addressMapper.convertToAddress(addressDTO);
        addressUpdating.setCountry(addressMapped.getCountry());
        addressUpdating.setCity(addressMapped.getCity());
        addressUpdating.setDistrict(addressMapped.getDistrict());
        addressUpdating.setWard(addressMapped.getWard());
        addressUpdating.setStreet(addressMapped.getStreet());
        addressUpdating.setUser(addressMapped.getUser());
        addressUpdating.setOrders(new ArrayList<>());
        Address addressUpdated = addressRepository.save(addressUpdating);
        return addressUpdated;
    }


}
