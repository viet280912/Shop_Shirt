package com.example.firstproject.controller;

import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.model.ProductDetail.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping("/add-new")
    public ProductDetailDTO createProductDetail(@RequestBody ProductDetailDTO productDetailDto) {
        return productDetailService.createProductDetail(productDetailDto);
    }

    @PutMapping("/{id}")
    public ProductDetailDTO updateProductDetail(@PathVariable int id, @RequestBody ProductDetailDTO productDetailDto) {
        return productDetailService.updateProductDetail(id, productDetailDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDetail(@PathVariable int id) {
        productDetailService.deleteProductDetail(id);
    }

    @GetMapping
    public List<ProductDetailDTO> getAllProductDetails() {
        return productDetailService.getAllProductDetails();
    }

    @GetMapping("/{id}")
    public ProductDetailDTO getProductDetailById(@PathVariable int id) {
        return productDetailService.getProductDetailById(id);
    }
}
