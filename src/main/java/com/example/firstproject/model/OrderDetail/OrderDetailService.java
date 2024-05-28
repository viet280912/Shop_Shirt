package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.dto.OrderDetailDTO;
import com.example.firstproject.model.Order.CreateOrder;
import com.example.firstproject.model.Order.CreateOrderDetail;

import java.util.List;
import java.util.Objects;

public interface OrderDetailService {
//    get order detail by id
    OrderDetailDTO getOrderDetailByID (int id);

//    get order by order id
    List<OrderDetailDTO> getOrdersDetailByOrderID (int id);

//    get orders detail by product detail
    List<OrderDetailDTO> getOrdersDetailByProductDetailID (int id);

//    get orders detail in range price


//    create order detail
    OrderDetail createOrderDetail (OrderDetailDTO orderDetail);

//    create order detail
    OrderDetail createOrderDetail (CreateOrderDetail order);

//    update order detail
    OrderDetail updateOrderDetail (OrderDetailDTO orderDetail);

//   delete order detail
    Object deleteOrderDetail (int id);
}
