package com.example.firstproject.mapper;

import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDetailMapper {
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

    @Mapping(source = "product.product_id", target = "productId")
    ProductDetailDTO toDto(ProductDetail productDetail);

    @Mapping(source = "productId", target = "product.product_id")
    ProductDetail toEntity(ProductDetailDTO productDetailDto);
}
