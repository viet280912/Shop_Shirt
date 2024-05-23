package com.example.firstproject.mapper;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.model.Address.Address;

public interface AddressMapper {
    Address convertToAddress(AddressDTO addressDTO);
    AddressDTO convertToAddressDTO(Address address);
}
