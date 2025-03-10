package io.github.nayetdet.catalog.mapper;

import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductDTO toDTO(Product product);

}
