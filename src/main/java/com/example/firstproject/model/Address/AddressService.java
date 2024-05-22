package com.example.firstproject.model.Address;

import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    Address createAddress(Address address, Integer userId);
}
