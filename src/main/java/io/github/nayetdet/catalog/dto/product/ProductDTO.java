package io.github.nayetdet.catalog.dto.product;

import io.github.nayetdet.catalog.dto.category.CategoryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Schema(name = "Product")
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;
    private CategoryDTO category;

}
