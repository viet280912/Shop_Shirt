package com.example.firstproject.mapper;
import com.example.firstproject.dto.OrderDetailDTO;
import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.model.OrderDetail.OrderDetail;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDetailToDTO implements Function<ProductDetail, ProductDetailDTO> {
    public OrderDetailDTO convertOrderDetailDTO(OrderDetail orderDetail) {
        return new OrderDetailDTO(
                orderDetail.getOrderDetail_id(),
                orderDetail.getQuantity(),
                orderDetail.getPrice(),
                apply(orderDetail.getProductDetail())
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
                0
        );
//        return null;
    }
}