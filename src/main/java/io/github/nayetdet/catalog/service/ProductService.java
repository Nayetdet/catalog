package io.github.nayetdet.catalog.service;

import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.exception.NotFoundException;
import io.github.nayetdet.catalog.mapper.ProductMapper;
import io.github.nayetdet.catalog.model.Product;
import io.github.nayetdet.catalog.repository.ProductRepository;
import io.github.nayetdet.catalog.repository.specs.ProductSpecs;
import io.github.nayetdet.catalog.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductValidator validator;

    public Page<ProductDTO> search(
            String name,
            String description,
            BigDecimal price,
            Integer pageNumber,
            Integer pageSize
    ) {
        Specification<Product> specs = Specification.where(null);
        if (name != null) specs = specs.and(ProductSpecs.nameLike(name));
        if (description != null) specs = specs.and(ProductSpecs.descriptionLike(description));
        if (price != null) specs = specs.and(ProductSpecs.priceEqual(price));

        var pageRequest = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(specs, pageRequest).map(mapper::toDTO);
    }

    public Optional<ProductDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public ProductDTO create(ProductRequestDTO productRequestDTO) {
        var product = mapper.toEntity(productRequestDTO);
        validator.validate(product);

        var createdProduct = repository.save(product);
        return mapper.toDTO(createdProduct);
    }

    public void update(Long id, ProductRequestDTO productRequestDTO) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        var product = mapper.toEntity(productRequestDTO);
        validator.validate(product);
        repository.save(product);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        repository.deleteById(id);
    }

}
