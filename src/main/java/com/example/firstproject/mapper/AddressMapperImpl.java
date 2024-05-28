package com.example.firstproject.mapper;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressMapperImpl implements AddressMapper{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Address convertToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddress_id(addressDTO.getId());
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setDistrict(addressDTO.getDistrict());
        address.setWard(addressDTO.getWard());
        address.setStreet(addressDTO.getStreet());
        User user = userRepository.findById(addressDTO.getUser_id())
                .orElseThrow(() -> new NotFoundException("Not found user with id: "+addressDTO.getUser_id()));
        address.setUser(user);
        return address;
    }

    @Override
    public AddressDTO convertToAddressDTO(Address address) {
        return new AddressDTO(
                address.getAddress_id(),
                address.getCountry(),
                address.getCity(),
                address.getDistrict(),
                address.getWard(),
                address.getStreet(),
                address.getUser().getUser_id()
        );
    }
}
