package com.example.firstproject.model.Order;

import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.mapper.OrderToOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderToOrderDTO orderToOrderDTO;

    @Override
    public OrderDTO getOrderByID(int order_id) {
        return orderToOrderDTO.apply(orderRepository.findOrderByID(order_id));
    }

    @Override
    public List<OrderDTO> getOrdersByUserID(int user_id) {
        List<Order> orders = orderRepository.findOrdersByUserID(user_id);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderToOrderDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByAddressID(int address_id) {
        List<Order> orders = orderRepository.findOrdersByAddressID(address_id);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderToOrderDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepository.findOrdersByStatus(status);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderToOrderDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByRangePrice(float x, float y) {
        List<Order> orders = orderRepository.findOrdersByRangePrice(x, y);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderToOrderDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByTime(Date time) {
        List<Order> orders = orderRepository.findOrdersByTime(time);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderToOrderDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersInTimes(Date time_x, Date time_y) {
        if (time_x == null) {
            return orderRepository.findOrdersToTime(time_y)
                    .stream()
                    .map(orderToOrderDTO)
                    .collect(Collectors.toList());
        } else if (time_y == null) {
            return orderRepository.findOrdersTimeTo(time_x)
                    .stream()
                    .map(orderToOrderDTO)
                    .collect(Collectors.toList());
        } else {
            return orderRepository.findOrdersInTimes(time_x, time_y)
                    .stream()
                    .map(orderToOrderDTO)
                    .collect(Collectors.toList());
        }
    }
}
