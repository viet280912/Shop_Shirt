package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.ProductMapper;
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
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductByID(int id) {
        Optional<Product> productCheck = productRepository.checkEmptyProduct(id);
        assert productCheck.orElse(null) != null;
        return productMapper.apply(productCheck.orElse(null));
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name, String category) {
        List<Product> products = productRepository.searchProduct(name, category);
        if (!products.isEmpty()) {
            return products.stream().map(productMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }
}
