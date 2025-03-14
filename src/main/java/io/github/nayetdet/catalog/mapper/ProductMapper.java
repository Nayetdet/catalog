package io.github.nayetdet.catalog.mapper;

import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.model.Category;
import io.github.nayetdet.catalog.model.Product;
import io.github.nayetdet.catalog.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public abstract class ProductMapper {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "findCategoryById")
    public abstract Product toEntity(ProductRequestDTO productRequestDTO);
    public abstract ProductDTO toDTO(Product product);

    @Named("findCategoryById")
    protected Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
