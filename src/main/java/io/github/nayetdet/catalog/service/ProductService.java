package io.github.nayetdet.catalog.service;

import static io.github.nayetdet.catalog.repository.specs.ProductSpecs.*;

import io.github.nayetdet.catalog.dto.product.ProductSearchDTO;
import io.github.nayetdet.catalog.model.Product;
import io.github.nayetdet.catalog.utils.page.CustomPage;
import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.exception.NotFoundException;
import io.github.nayetdet.catalog.mapper.ProductMapper;
import io.github.nayetdet.catalog.repository.ProductRepository;
import io.github.nayetdet.catalog.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductValidator productValidator;

    public CustomPage<ProductDTO> search(ProductSearchDTO productSearchDTO) {
        Specification<Product> specs = Specification.where(null);
        if (productSearchDTO.getName() != null) specs = specs.and(nameLike(productSearchDTO.getName()));
        if (productSearchDTO.getDescription() != null) specs = specs.and(descriptionLike(productSearchDTO.getDescription()));
        if (productSearchDTO.getPrice() != null) specs = specs.and(priceEqual(productSearchDTO.getPrice()));

        var pageRequest = PageRequest.of(productSearchDTO.getPageNumber(), productSearchDTO.getPageSize());
        return new CustomPage<>(productRepository.findAll(specs, pageRequest).map(productMapper::toDTO));
    }

    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toDTO);
    }

    public ProductDTO create(ProductRequestDTO productRequestDTO) {
        var product = productMapper.toEntity(productRequestDTO);
        productValidator.validate(product);

        var createdProduct = productRepository.save(product);
        return productMapper.toDTO(createdProduct);
    }

    public void update(Long id, ProductRequestDTO productRequestDTO) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        var updatedProduct = productMapper.toEntity(productRequestDTO);
        updatedProduct.setId(id);
        updatedProduct.setCreatedAt(product.getCreatedAt());

        productValidator.validate(updatedProduct);
        productRepository.save(updatedProduct);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }

        productRepository.deleteById(id);
    }

}
