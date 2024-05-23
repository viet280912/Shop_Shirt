package com.example.firstproject.model.ProductDetail;

import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.mapper.ProductDetailMapper;
import com.example.firstproject.model.Product.Product;
import com.example.firstproject.model.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDetailDTO createProductDetail(ProductDetailDTO productDetailDto) {
        Product product = productRepository.findById(productDetailDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDetail productDetail = ProductDetailMapper.INSTANCE.toEntity(productDetailDto);
        productDetail.setProduct(product);
        productDetail = productDetailRepository.save(productDetail);
        return ProductDetailMapper.INSTANCE.toDto(productDetail);
    }

    @Override
    public ProductDetailDTO updateProductDetail(int productDetailId, ProductDetailDTO productDetailDto) {
        ProductDetail existingProductDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));

        Product product = productRepository.findById(productDetailDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProductDetail.setSize(productDetailDto.getSize());
        existingProductDetail.setColor(productDetailDto.getColor());
        existingProductDetail.setStock(productDetailDto.getStock());
        existingProductDetail.setImage(productDetailDto.getImage());
        existingProductDetail.setProduct(product);

        existingProductDetail = productDetailRepository.save(existingProductDetail);
        return ProductDetailMapper.INSTANCE.toDto(existingProductDetail);
    }

    @Override
    public void deleteProductDetail(int productDetailId) {
        ProductDetail productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
        productDetailRepository.delete(productDetail);
    }

    @Override
    public List<ProductDetailDTO> getAllProductDetails() {
        return productDetailRepository.findAll().stream()
                .map(ProductDetailMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailDTO getProductDetailById(int productDetailId) {
        ProductDetail productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
        return ProductDetailMapper.INSTANCE.toDto(productDetail);
    }
}
