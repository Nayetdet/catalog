package io.github.nayetdet.catalog.mapper;

import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.model.Product;
import io.github.nayetdet.catalog.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public abstract class ProductMapper {

    @Autowired
    CategoryRepository categoryRepository;

    @Mapping(target = "category", expression = "java(categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null))")
    public abstract Product toEntity(ProductRequestDTO productRequestDTO);

    @Mapping(target = "categoryDTO", source = "category")
    public abstract ProductDTO toDTO(Product product);

}
