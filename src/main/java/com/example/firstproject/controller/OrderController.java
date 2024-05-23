package com.example.firstproject.controller;

import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.model.Order.OrderService;
import com.example.firstproject.model.OrderDetail.OrderDetailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

//    get all and search by range time
    ResponseEntity<?> getAll (
            @RequestBody RangeTime rangeTime
    ) {
        try {
            List<OrderDTO> orders = orderService.getOrdersInTimes(rangeTime.getTime_x(), rangeTime.getTime_y());
            if (!orders.isEmpty()) {
                return new ResponseEntity<>(
                        orders,
                        HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    "Empty List",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.OK
            );
        }

    }
//    get order by user_id or order_id
//    ResponseEntity<?> findOrderByID (
//
//    )
}
@AllArgsConstructor
@NoArgsConstructor
@Data
class RangeTime {
    private Date time_x;
    private Date time_y;
}
