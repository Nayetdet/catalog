package io.github.nayetdet.catalog.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.nayetdet.catalog.dto.category.CategoryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "Product")
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonProperty("category")
    private CategoryDTO categoryDTO;

}
