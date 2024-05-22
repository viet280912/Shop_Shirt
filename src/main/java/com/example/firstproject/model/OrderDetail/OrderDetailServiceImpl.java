package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.dto.OrderDetailDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.OrderToOrderDTO;
import com.example.firstproject.mapper.ProductToProductDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductToProductDetailDTO productDetailDTO;

    @Override
    public OrderDetailDTO getOrderDetailByID(int id) {
        if (orderDetailRepository.checkEmptyOrderDetail(id).isPresent()) {
            return productDetailDTO.convertOrderDetailDTO(orderDetailRepository.getOrderDetailByID(id));
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDetailDTO> getOrdersDetailByOrderID(int id) {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailByOrderID(id);
        if (!orderDetails.isEmpty()) {
            return orderDetails.stream().map(productDetailDTO::convertOrderDetailDTO).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public List<OrderDetailDTO> getOrdersDetailByProductDetailID(int id) {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailByProductDetailID(id);
        if (!orderDetails.isEmpty()) {
            return orderDetails.stream().map(productDetailDTO::convertOrderDetailDTO).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }
}
