package io.github.nayetdet.catalog.validator;

import io.github.nayetdet.catalog.exception.DuplicateEntryException;
import io.github.nayetdet.catalog.exception.NotFoundException;
import io.github.nayetdet.catalog.model.Product;
import io.github.nayetdet.catalog.repository.CategoryRepository;
import io.github.nayetdet.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductValidator {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public void validate(Product product) {
        if (isCategoryEmpty(product)) {
            throw new NotFoundException("Category not found");
        }

        if (isNameDuplicated(product)) {
            throw new DuplicateEntryException("A product with the same name already exists");
        }
    }

    private boolean isCategoryEmpty(Product product) {
        return !categoryRepository.existsById(product.getCategory().getId());
    }

    private boolean isNameDuplicated(Product product) {
        return productRepository.existsByName(product.getName());
    }

}
