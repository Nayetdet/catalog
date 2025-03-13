package io.github.nayetdet.catalog.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "ProductSearch")
public class ProductSearchDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer pageNumber = 0;
    private Integer pageSize = 10;

}
