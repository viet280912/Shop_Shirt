package com.example.firstproject.mapper;


import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.dto.OrderDetailDTO;
import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.model.Order.Order;
import com.example.firstproject.model.OrderDetail.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderToOrderDTO implements Function<Order, OrderDTO> {
    @Autowired
    private ProductToProductDetailDTO productDetailDTO;

    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getOrder_id(),
                order.getOrder_dateTime(),
                order.getTotal_price(),
                order.getStatus(),
                order.getOrderDetails().stream().map(productDetailDTO::convertOrderDetailDTO).collect(Collectors.toList())
        );
    }
}
