package io.github.nayetdet.catalog.repository.specs;

import io.github.nayetdet.catalog.model.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecs {

    public static Specification<Category> nameLike(String name) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }

}
