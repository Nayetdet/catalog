package io.github.nayetdet.catalog.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CategoryRequest")
public class CategoryRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

}
