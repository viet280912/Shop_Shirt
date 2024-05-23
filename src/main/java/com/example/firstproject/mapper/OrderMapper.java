package com.example.firstproject.mapper;


import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.Order.Order;
import com.example.firstproject.model.Order.OrderRepository;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderMapper implements Function<Order, OrderDTO> {
    @Autowired
    private ProductDetailToDTO productDetailDTO;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getOrder_id(),
                order.getOrder_dateTime(),
                order.getTotal_price(),
                order.getStatus(),
                order.getOrderDetails().stream().map(productDetailDTO::convertOrderDetailDTO).collect(Collectors.toList()),
                order.getUser().getUser_id(),
                order.getAddress().getAddress_id()
        );
    }

    public Order convertToOrder(OrderDTO order) {
        User user = userRepository.findById(order.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+order.getUser_id()));
        Address address = addressRepository.findById(order.getAddress_id())
                .orElseThrow(() -> new EntityNotFoundException("Not found address with id: "+order.getAddress_id()));

        return new Order(
                order.getOrder_id(),
                order.getOrder_dateTime(),
                order.getTotal_price(),
                order.getStatus(),
                user,
                address,
                order.getOrdersDetail()
                        .stream()
                        .map(productDetailDTO::convertToOrderDetail)
                        .collect(Collectors.toList())
        );
    }
}
