package com.example.firstproject.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private int id;

    private String country;

    private String city;

    private String district;

    private String ward;

    private String street;

}
