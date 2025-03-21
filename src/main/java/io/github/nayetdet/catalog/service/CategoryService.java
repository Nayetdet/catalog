package io.github.nayetdet.catalog.service;

import static io.github.nayetdet.catalog.repository.specs.CategorySpecs.*;

import io.github.nayetdet.catalog.dto.category.CategoryDTO;
import io.github.nayetdet.catalog.dto.category.CategoryRequestDTO;
import io.github.nayetdet.catalog.dto.category.CategorySearchDTO;
import io.github.nayetdet.catalog.exception.NotFoundException;
import io.github.nayetdet.catalog.mapper.CategoryMapper;
import io.github.nayetdet.catalog.model.Category;
import io.github.nayetdet.catalog.repository.CategoryRepository;
import io.github.nayetdet.catalog.utils.page.CustomPage;
import io.github.nayetdet.catalog.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidator categoryValidator;

    public CustomPage<CategoryDTO> search(CategorySearchDTO categorySearchDTO) {
        Specification<Category> specs = Specification.where(null);
        if (categorySearchDTO.getName() != null) specs.and(nameLike(categorySearchDTO.getName()));

        var pageRequest = PageRequest.of(categorySearchDTO.getPageNumber(), categorySearchDTO.getPageSize());
        return new CustomPage<>(categoryRepository.findAll(specs, pageRequest).map(categoryMapper::toDTO));
    }

    public Optional<CategoryDTO> findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO);
    }

    public CategoryDTO create(CategoryRequestDTO categoryRequestDTO) {
        var category = categoryMapper.toEntity(categoryRequestDTO);
        categoryValidator.validate(category);

        var createdCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(createdCategory);
    }

    public void update(Long id, CategoryRequestDTO categoryRequestDTO) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }

        var updatedCategory = categoryMapper.toEntity(categoryRequestDTO);
        updatedCategory.setId(id);
        updatedCategory.setCreatedAt(category.getCreatedAt());

        categoryValidator.validate(updatedCategory);
        categoryRepository.save(updatedCategory);
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found");
        }

        categoryRepository.deleteById(id);
    }

}
