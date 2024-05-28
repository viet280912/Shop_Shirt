package com.example.firstproject.model.Address;

import com.example.firstproject.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    Address createAddress(AddressDTO addressDTO);
    List<Address> getAllAddressByUserId(Integer userId);
    Address updateAddress(Integer addressId, AddressDTO addressDTO);
    void deleteOneAddress(Integer addressId);
    void deleteManyAddress(List<Integer> listId);
}
