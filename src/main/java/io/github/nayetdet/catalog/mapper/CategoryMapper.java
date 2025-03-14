package io.github.nayetdet.catalog.mapper;

import io.github.nayetdet.catalog.dto.category.CategoryDTO;
import io.github.nayetdet.catalog.dto.category.CategoryRequestDTO;
import io.github.nayetdet.catalog.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO categoryRequestDTO);
    CategoryDTO toDTO(Category category);

}
