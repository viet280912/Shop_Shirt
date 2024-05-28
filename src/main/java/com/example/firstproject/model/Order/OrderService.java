package com.example.firstproject.model.Order;

import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.model.Status.Status;

import java.util.Date;
import java.util.List;

public interface OrderService {
//    get all order
    List<OrderDTO> getAllOrder ();

//    get order by order id
    OrderDTO getOrderByID(int order_id);

//    get order by user id
    List<OrderDTO> getOrdersByUserID (int user_id);

//    get order by address id
    List<OrderDTO> getOrdersByAddressID (int address_id);

//    get order by status
    List<OrderDTO> getOrdersByStatus (String status);

//    get order by user_id, address_id, status
    List<OrderDTO> findOrderByForm (int user_id, int address_id, String status);

//    get orders by range price (x < orders < y)
    List<OrderDTO> getOrdersByRangePrice (float x, float y);

//    get orders by current time
    List<OrderDTO> getOrdersByTime (Date time);

//    get orders in times
    List<OrderDTO> getOrdersInTimes (Date time_x, Date time_y);

//    create order from DTO
    Order createOrder (OrderDTO order);

//    create order from CreateOrder
    Order createOrder (CreateOrder order);


//    update order
    Order updateOrder (OrderDTO orderDTO);

//    delete order
    void deleteOrder (int id);
}
