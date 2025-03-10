package io.github.nayetdet.catalog.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 300)
    private String description;

    @NotNull
    private BigDecimal price;

}
