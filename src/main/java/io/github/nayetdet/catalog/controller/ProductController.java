package io.github.nayetdet.catalog.controller;

import io.github.nayetdet.catalog.controller.docs.ProductControllerDocs;
import io.github.nayetdet.catalog.dto.product.ProductDTO;
import io.github.nayetdet.catalog.dto.product.ProductRequestDTO;
import io.github.nayetdet.catalog.dto.product.ProductSearchDTO;
import io.github.nayetdet.catalog.service.ProductService;
import io.github.nayetdet.catalog.utils.page.CustomPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Endpoints for managing products")
public class ProductController extends AbstractController implements ProductControllerDocs {

    private final ProductService productService;

    @Override
    @GetMapping
    public ResponseEntity<CustomPage<ProductDTO>> search(@ParameterObject ProductSearchDTO productSearchDTO) {
        if (productSearchDTO == null) {
            productSearchDTO = new ProductSearchDTO();
        }

        var productDTOs = productService.search(productSearchDTO);
        return ResponseEntity.ok(productDTOs);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        var productDTO = productService.findById(id);
        return productDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        var productDTO = productService.create(productRequestDTO);
        var uri = getHeaderLocation(productDTO.getId());
        return ResponseEntity.created(uri).body(productDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        productService.update(id, productRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
