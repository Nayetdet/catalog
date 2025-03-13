package io.github.nayetdet.catalog.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CategorySearch")
public class CategorySearchDTO {

    private String name;
    private Integer pageNumber = 0;
    private Integer pageSize = 10;

}
