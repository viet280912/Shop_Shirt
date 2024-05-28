package com.example.firstproject.controller;

import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.model.Order.Order;
import com.example.firstproject.model.Order.OrderService;
import com.example.firstproject.model.OrderDetail.OrderDetailService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

//    get all and search by range time
    @GetMapping("/all")
    ResponseEntity<?> getAll (
            @Nullable
            @RequestBody RangeTime rangeTime
    ) {
        try {
            if (rangeTime != null) {
                List<OrderDTO> orders = orderService.getOrdersInTimes(rangeTime.getTime_x(), rangeTime.getTime_y());
                if (!orders.isEmpty()) {
                    return new ResponseEntity<>(
                            orders,
                            HttpStatus.OK
                    );
                }
                return new ResponseEntity<>(
                        "Empty List",
                        HttpStatus.NOT_FOUND
                );
            } else {
                List<OrderDTO> orders = orderService.getAllOrder();
                if (!orders.isEmpty()) {
                    return new ResponseEntity<>(
                            orders,
                            HttpStatus.OK
                    );
                }
                return new ResponseEntity<>(
                        "Empty List",
                        HttpStatus.NOT_FOUND
                );
            }

        } catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.BAD_REQUEST
            );
        }

    }
//    get order by user_id or order_id
//    ResponseEntity<?> findOrderByID (
//
//    )
//    create order
    @PostMapping("/add")
    ResponseEntity<?> createOrder (
            OrderDTO orderDTO
    ) {
        Order order = orderService.createOrder(orderDTO);
        if (order == null) {
            return new ResponseEntity<>(
                    order,
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "Order failure",
                HttpStatus.FAILED_DEPENDENCY
        );
    }
}
@AllArgsConstructor
@NoArgsConstructor
@Data
class RangeTime {
    private Date time_x;
    private Date time_y;
}
