package com.example.firstproject.controller;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.model.Address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllAddressByUserId(@PathVariable Integer id){
        return new ResponseEntity<>(addressService.getAllAddressByUserId(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateAddress(
            @PathVariable Integer id,
            @RequestBody AddressDTO addressDTO
    ){
        return new ResponseEntity<>(addressService.updateAddress(id, addressDTO), HttpStatus.OK);
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
