package com.example.firstproject.model.OrderDetail;

import com.example.firstproject.dto.OrderDetailDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.ProductDetailMapper;
import com.example.firstproject.mapper.ProductDetailToDTO;
import com.example.firstproject.model.Order.CreateOrder;
import com.example.firstproject.model.Order.CreateOrderDetail;
import com.example.firstproject.model.Order.OrderRepository;
import com.example.firstproject.model.ProductDetail.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductDetailToDTO productDetailDTO;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

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

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) {
        return orderDetailRepository.save(productDetailDTO.convertToOrderDetail(orderDetailDTO));
    }

    @Override
    public OrderDetail createOrderDetail(CreateOrderDetail order) {
        OrderDetail detail = new OrderDetail(
                orderRepository.findOrderByID(order.getOrder_id()),
                productDetailRepository.findItemByID(order.getProduct_detail_id()),
                order.getQuantity(),
                order.getPrice()
        );
        if (detail != null) {
            return orderDetailRepository.save(detail);
        }

        throw new NotFoundException("Error");
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetailDTO orderDetail) {
        OrderDetail orderDetailUpdate = productDetailDTO.convertToOrderDetail(orderDetail);

        return orderDetailRepository.save(orderDetailUpdate);
    }

    @Override
    public Object deleteOrderDetail(int id) {
        try {
            orderDetailRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            return e;
        }
    }
}
