package com.example.firstproject.model.ProductDetail;

import com.example.firstproject.dto.ProductDetailDTO;

import java.util.List;

public interface ProductDetailService {
    ProductDetailDTO createProductDetail(ProductDetailDTO productDetailDto);
    ProductDetailDTO updateProductDetail(int productDetailId, ProductDetailDTO productDetailDto);
    void deleteProductDetail(int productDetailId);
    List<ProductDetailDTO> getAllProductDetails();
    ProductDetailDTO getProductDetailById(int productDetailId);
}
