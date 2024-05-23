package com.example.firstproject.mapper;
import com.example.firstproject.dto.OrderDetailDTO;
import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.model.Order.OrderRepository;
import com.example.firstproject.model.OrderDetail.OrderDetail;
import com.example.firstproject.model.OrderDetail.OrderDetailRepository;
import com.example.firstproject.model.OrderDetail.OrderDetailService;
import com.example.firstproject.model.Product.ProductRepository;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import com.example.firstproject.model.ProductDetail.ProductDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductDetailToDTO implements Function<ProductDetail, ProductDetailDTO> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailDTO convertOrderDetailDTO(OrderDetail orderDetail) {
        return new OrderDetailDTO(
                orderDetail.getOrderDetail_id(),
                orderDetail.getQuantity(),
                orderDetail.getOrder().getOrder_id(),
                orderDetail.getPrice(),
                apply(orderDetail.getProductDetail())
        );
    }
    public OrderDetail convertToOrderDetail(OrderDetailDTO orderDetailDTO) {
        return new OrderDetail(
                orderDetailDTO.getOrderDetail_id(),
                orderRepository.findById(orderDetailDTO.getOrder_id()).orElseThrow(() -> new EntityNotFoundException("Not found order with id: " + orderDetailDTO.getOrder_id())),
                convertToProductDetail(orderDetailDTO.getProductDetail()),
                orderDetailDTO.getQuantity(),
                orderDetailDTO.getPrice()

        );
    }
    public ProductDetail convertToProductDetail(ProductDetailDTO productDetailDTO) {
        return new ProductDetail(
                productDetailDTO.getProductDetailId(),
                productDetailDTO.getSize(),
                productDetailDTO.getColor(),
                productDetailDTO.getStock(),
                productDetailDTO.getImage(),
                orderDetailRepository.getOrderDetailByProductDetailID(productDetailDTO.getProductDetailId()),
                productRepository.findById(productDetailDTO.getProductDetailId())
                        .orElseThrow(() -> new EntityNotFoundException("Not found user with id: "+productDetailDTO.getProductId()))
        );
    }


    @Override
    public ProductDetailDTO apply(ProductDetail productDetail) {
        return new ProductDetailDTO(
                productDetail.getProductDetail_id(),
                productDetail.getSize(),
                productDetail.getColor(),
                productDetail.getStock(),
                productDetail.getImage(),
                productDetail.getProduct().getProduct_id()
        );
//        return null;
    }

}