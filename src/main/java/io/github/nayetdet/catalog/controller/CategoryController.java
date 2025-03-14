package io.github.nayetdet.catalog.controller;

import io.github.nayetdet.catalog.controller.docs.CategoryControllerDocs;
import io.github.nayetdet.catalog.dto.category.CategoryDTO;
import io.github.nayetdet.catalog.dto.category.CategoryRequestDTO;
import io.github.nayetdet.catalog.dto.category.CategorySearchDTO;
import io.github.nayetdet.catalog.service.CategoryService;
import io.github.nayetdet.catalog.utils.page.CustomPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "Endpoints for managing product categories")
public class CategoryController extends AbstractController implements CategoryControllerDocs {

    private final CategoryService categoryService;

    @Override
    @GetMapping
    public ResponseEntity<CustomPage<CategoryDTO>> search(@ParameterObject CategorySearchDTO categorySearchDTO) {
        if (categorySearchDTO == null) {
            categorySearchDTO = new CategorySearchDTO();
        }

        var categoryDTOs = categoryService.search(categorySearchDTO);
        return ResponseEntity.ok(categoryDTOs);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        var categoryDTO = categoryService.findById(id);
        return categoryDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        var categoryDTO = categoryService.create(categoryRequestDTO);
        var uri = getHeaderLocation(categoryDTO.getId());
        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        categoryService.update(id, categoryRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
