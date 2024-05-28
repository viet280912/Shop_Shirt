package com.example.firstproject.model.Order;

import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.OrderMapper;
import com.example.firstproject.model.Address.Address;
import com.example.firstproject.model.Address.AddressRepository;
import com.example.firstproject.model.OrderDetail.OrderDetailRepository;
import com.example.firstproject.model.OrderDetail.OrderDetailService;
import com.example.firstproject.model.User.User;
import com.example.firstproject.model.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.firstproject.model.Status.Status.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public OrderDTO getOrderByID(int order_id) {
        return orderMapper.apply(orderRepository.findOrderByID(order_id));
    }

    @Override
    public List<OrderDTO> getOrdersByUserID(int user_id) {
        List<Order> orders = orderRepository.findOrdersByUserID(user_id);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDTO> getOrdersByAddressID(int address_id) {
        List<Order> orders = orderRepository.findOrdersByAddressID(address_id);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepository.findOrdersByStatus(status);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDTO> findOrderByForm(int user_id, int address_id, String status) {
        List<Order> orders = orderRepository.findOrdersByForm(user_id, address_id, status);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDTO> getOrdersByRangePrice(float x, float y) {
        List<Order> orders = orderRepository.findOrdersByRangePrice(x, y);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDTO> getOrdersByTime(Date time) {
        List<Order> orders = orderRepository.findOrdersByTime(time);
        if (!orders.isEmpty()) {
            return orders.stream().map(orderMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDTO> getOrdersInTimes(Date time_x, Date time_y) {
        if (time_x == null) {
            return orderRepository.findOrdersToTime(time_y)
                    .stream()
                    .map(orderMapper)
                    .collect(Collectors.toList());
        } else if (time_y == null) {
            return orderRepository.findOrdersTimeTo(time_x)
                    .stream()
                    .map(orderMapper)
                    .collect(Collectors.toList());
        } else {
            return orderRepository.findOrdersInTimes(time_x, time_y)
                    .stream()
                    .map(orderMapper)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Order createOrder(OrderDTO order) {
        try {
            Order newOrder = orderMapper.convertToOrder(order);

            return orderRepository.save(newOrder);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Failed to save order: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Order createOrder(CreateOrder createOrder) {
        try {
            User user = userRepository.findById(createOrder.getUser_id())
                    .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+createOrder.getUser_id()));

            Address address = addressRepository.findById(createOrder.getAddress_id())
                    .orElseThrow(() -> new EntityNotFoundException("Not found address with id: "+createOrder.getAddress_id()));

            Order newOrder = new Order(
                    LocalDateTime.now(),
                    createOrder.getTotal_price(),
                    PENDING,
                    user,
                    address
            );

            return orderRepository.save(newOrder);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Failed to save order: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Order updateOrder(OrderDTO orderDTO) {
        Order order = orderMapper.convertToOrder(orderDTO);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
