package io.github.nayetdet.catalog.mapper;

import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-13T21:54:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class ProductMapperImpl extends ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Product toEntity(ProductRequestDTO productRequestDTO) {
        if ( productRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( findCategoryById( productRequestDTO.getCategoryId() ) );
        product.setName( productRequestDTO.getName() );
        product.setDescription( productRequestDTO.getDescription() );
        product.setPrice( productRequestDTO.getPrice() );

        return product;
    }

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setCreatedAt( product.getCreatedAt() );
        productDTO.setUpdatedAt( product.getUpdatedAt() );
        productDTO.setCategory( categoryMapper.toDTO( product.getCategory() ) );

        return productDTO;
    }
}
