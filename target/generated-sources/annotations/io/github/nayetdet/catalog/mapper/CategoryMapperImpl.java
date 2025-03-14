package io.github.nayetdet.catalog.mapper;

import io.github.nayetdet.catalog.dto.category.CategoryDTO;
import io.github.nayetdet.catalog.dto.category.CategoryRequestDTO;
import io.github.nayetdet.catalog.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-13T21:25:53-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        if ( categoryRequestDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryRequestDTO.getName() );

        return category;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setCreatedAt( category.getCreatedAt() );
        categoryDTO.setUpdatedAt( category.getUpdatedAt() );

        return categoryDTO;
    }
}
