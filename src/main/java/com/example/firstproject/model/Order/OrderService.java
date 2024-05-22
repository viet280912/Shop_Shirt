package com.example.firstproject.model.Order;

import com.example.firstproject.dto.OrderDTO;

import java.util.Date;
import java.util.List;

public interface OrderService {
//    get order by order id
    OrderDTO getOrderByID(int order_id);

//    get order by user id
    List<OrderDTO> getOrdersByUserID (int user_id);

//    get order by address id
    List<OrderDTO> getOrdersByAddressID (int address_id);

//    get order by status
    List<OrderDTO> getOrdersByStatus (String status);

//    get orders by range price (x < orders < y)
    List<OrderDTO> getOrdersByRangePrice (float x, float y);

//    get orders by current time
    List<OrderDTO> getOrdersByTime (Date time);

//    get orders in times
    List<OrderDTO> getOrdersInTimes (Date time_x, Date time_y);
}
