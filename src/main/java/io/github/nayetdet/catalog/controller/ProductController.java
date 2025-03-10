package io.github.nayetdet.catalog.controller;

import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(name = "page-number", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "page-size", defaultValue = "10") Integer pageSize
    ) {
        var productDTOs = service.search(name, description, price, pageNumber, pageSize);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        var productDTO = service.findById(id);
        return productDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        var productDTO = service.create(productRequestDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        service.update(id, productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
