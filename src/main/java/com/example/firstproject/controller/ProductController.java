package com.example.firstproject.controller;

import com.example.firstproject.dto.OrderDTO;
import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.model.Product.DataProduct;
import com.example.firstproject.model.Product.ProductService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

//    get all
    @GetMapping("/all")
    ResponseEntity<?> getAllProduct (
            @Nullable
            @RequestBody ProductPrice productPrice,
            @RequestParam int page
    ) {
        DataProduct productDTOS;
        if (productPrice != null ) {
            productDTOS = productService.getProductsInRangePrice(productPrice.getPrice_x(), productPrice.getPrice_y(), page);
        } else {
            productDTOS = productService.getAll(page);
        }
        return new ResponseEntity<>(
                productDTOS,
                HttpStatus.OK
        );

    }

//    get product by name and category
    @GetMapping("/search")
    ResponseEntity<?> getAllProductByNameAndCategory (
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam int page
    ) {
        List<ProductDTO> productDTOS = productService.searchProductsByName(name, category, page);

        if (!productDTOS.isEmpty()) {
            return new ResponseEntity<>(
                    productDTOS,
                    HttpStatus.OK
            );
        }
        throw new NotFoundException("Empty list");
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class ProductPrice {
    private Float price_x;
    private Float price_y;
}

