package io.github.nayetdet.catalog.validator;

import io.github.nayetdet.catalog.exception.DuplicateEntryException;
import io.github.nayetdet.catalog.model.Product;
import io.github.nayetdet.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductValidator {

    private final ProductRepository repository;

    public void validate(Product product) {
        if (repository.existsByName(product.getName())) {
            throw new DuplicateEntryException("A product with the name '" + product.getName() + "' already exists");
        }
    }

}
