package com.example.firstproject.model.Address;

import com.example.firstproject.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends
        JpaRepository<Address, Integer> ,
        PagingAndSortingRepository<Address, Integer> {
//    List<Address> findAddressByUserUser_id(Integer id);
    List<Address> findAllByUser(User user);
}
