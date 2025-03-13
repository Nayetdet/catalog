package io.github.nayetdet.catalog.validator;

import io.github.nayetdet.catalog.exception.DuplicateEntryException;
import io.github.nayetdet.catalog.model.Category;
import io.github.nayetdet.catalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public void validate(Category category) {
        if (isNameDuplicated(category)) {
            throw new DuplicateEntryException("A category with the same name already exists");
        }
    }

    private boolean isNameDuplicated(Category category) {
        return categoryRepository.existsByName(category.getName());
    }

}
