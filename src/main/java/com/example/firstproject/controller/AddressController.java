package com.example.firstproject.controller;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;
    @GetMapping
    public ResponseEntity<?> getContentApi(){
        return new ResponseEntity<>("addresses", HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllAddressByUserId(@PathVariable Integer id){
        List<Address> addresses = addressService.getAllAddressByUserId(id);
        List<AddressDTO> addressDTOS = addresses
                .stream()
                .map(a -> addressMapper.convertToAddressDTO(a))
                .toList();
        return new ResponseEntity<>(addressDTOS, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO addressDTO){
        Address address = addressService.createAddress(addressDTO);
        return new ResponseEntity<>(addressMapper.convertToAddressDTO(address), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateAddress(
            @PathVariable Integer id,
            @RequestBody AddressDTO addressDTO
    ){
        return new ResponseEntity<>(addressMapper.convertToAddressDTO(addressService.updateAddress(id, addressDTO)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOneAddress(
            @PathVariable Integer id
    ){
        addressService.deleteOneAddress(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteManyAddress(
            @RequestBody List<Integer> listId
            ){
        addressService.deleteManyAddress(listId);
        return  new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
}
