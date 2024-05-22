package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.mapper.ProductToProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductToProductDTO productToProductDTO;

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productToProductDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductByID(int id) {
        Optional<Product> productCheck = productRepository.checkEmptyProduct(id);
        assert productCheck.orElse(null) != null;
        return productToProductDTO.apply(productCheck.orElse(null));
    }
}
