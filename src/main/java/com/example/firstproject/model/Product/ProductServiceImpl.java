package com.example.firstproject.model.Product;

import com.example.firstproject.dto.ProductDTO;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.CategoryToDTO;
import com.example.firstproject.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public DataProduct getAll(int page) {
        Pageable pageable = PageRequest.of(page, 100);
        try {
            Page<Product> productPageable = productRepository.getProductPage(pageable);
            List<ProductDTO> productDTOS;

            if (!productPageable.isEmpty()) {
                productDTOS = productPageable
                        .getContent()
                        .stream()
                        .map(productMapper).collect(Collectors.toList());
                return new DataProduct(
                        productDTOS.size(),
                        page,
                        page,
                        "Successfully",
                        productDTOS
                );
            }
            return new DataProduct(
                    0,
                    page,
                    page,
                    "Successfully",
                    new ArrayList<>()
            );
        } catch (Exception e) {
            return new DataProduct(
                    0,
                    page,
                    0,
                    e.getMessage(),
                    new ArrayList<>()
            );
        }
    }

    @Override
    public ProductDTO getProductByID(int id) {
        Optional<Product> productCheck = productRepository.checkEmptyProduct(id);
        assert productCheck.orElse(null) != null;
        return productMapper.apply(productCheck.orElse(null));
    }

    @Override
    public DataProduct getProductsInRangePrice(Float x, Float y, int page) {
        Pageable pageable = PageRequest.of(page, 100);

        try {
            DataProduct product;
            Page<Product> productPageable = productRepository.getProductInRangePrice(x, y, pageable);
            List<ProductDTO> productDTOS;

            if (!productPageable.isEmpty()) {
                productDTOS = productPageable
                        .getContent()
                        .stream()
                        .map(productMapper).collect(Collectors.toList());
                return new DataProduct(
                        productDTOS.size(),
                        page,
                        page,
                        "Successfully",
                        productDTOS
                );
            }
            return new DataProduct(
                    0,
                    page,
                    page,
                    "Successfully",
                    new ArrayList<>()
            );
        } catch (Exception e) {
            return new DataProduct(
                    0,
                    page,
                    0,
                    e.getMessage(),
                    new ArrayList<>()
            );
        }
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name, String category, int page) {
        Pageable pageable = PageRequest.of(page, 100);
        Page<Product> products = productRepository.searchProduct(name, category, pageable);
        if (!products.getContent().isEmpty()) {
            return products.stream().map(productMapper).collect(Collectors.toList());
        }
        throw new NotFoundException("Empty");
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = productMapper.convertProduct(productDTO);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Product product = productMapper.convertProduct(productDTO);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
