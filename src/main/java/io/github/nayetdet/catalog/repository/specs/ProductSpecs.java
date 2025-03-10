package io.github.nayetdet.catalog.repository.specs;

import io.github.nayetdet.catalog.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecs {

    public static Specification<Product> nameLike(String name) {
        return (root, query, cb) ->
                cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> descriptionLike(String description) {
        return (root, query, cb) ->
                cb.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Product> priceEqual(BigDecimal price) {
        return (root, query, cb) ->
                cb.equal(root.get("price"), price);
    }

}
