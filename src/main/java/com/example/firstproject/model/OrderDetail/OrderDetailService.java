package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
//    get order detail by id
    OrderDetailDTO getOrderDetailByID (int id);

//    get order by order id
    List<OrderDetailDTO> getOrdersDetailByOrderID (int id);

//    get orders detail by product detail
    List<OrderDetailDTO> getOrdersDetailByProductDetailID (int id);

//    get orders detail in range price
}
