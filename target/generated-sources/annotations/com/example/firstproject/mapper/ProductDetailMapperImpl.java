package com.example.firstproject.mapper;

import com.example.firstproject.dto.ProductDetailDTO;
import com.example.firstproject.model.Product.Product;
import com.example.firstproject.model.ProductDetail.ProductDetail;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-31T16:05:36+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class ProductDetailMapperImpl implements ProductDetailMapper {

    @Override
    public ProductDetailDTO toDto(ProductDetail productDetail) {
        if ( productDetail == null ) {
            return null;
        }

        ProductDetailDTO productDetailDTO = new ProductDetailDTO();

        productDetailDTO.setProductId( productDetailProductProduct_id( productDetail ) );
        productDetailDTO.setSize( productDetail.getSize() );
        productDetailDTO.setColor( productDetail.getColor() );
        productDetailDTO.setStock( productDetail.getStock() );
        productDetailDTO.setImage( productDetail.getImage() );

        return productDetailDTO;
    }

    @Override
    public ProductDetail toEntity(ProductDetailDTO productDetailDto) {
        if ( productDetailDto == null ) {
            return null;
        }

        ProductDetail productDetail = new ProductDetail();

        productDetail.setProduct( productDetailDTOToProduct( productDetailDto ) );
        productDetail.setSize( productDetailDto.getSize() );
        productDetail.setColor( productDetailDto.getColor() );
        productDetail.setStock( productDetailDto.getStock() );
        productDetail.setImage( productDetailDto.getImage() );

        return productDetail;
    }

    private int productDetailProductProduct_id(ProductDetail productDetail) {
        if ( productDetail == null ) {
            return 0;
        }
        Product product = productDetail.getProduct();
        if ( product == null ) {
            return 0;
        }
        int product_id = product.getProduct_id();
        return product_id;
    }

    protected Product productDetailDTOToProduct(ProductDetailDTO productDetailDTO) {
        if ( productDetailDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setProduct_id( productDetailDTO.getProductId() );

        return product;
    }
}
