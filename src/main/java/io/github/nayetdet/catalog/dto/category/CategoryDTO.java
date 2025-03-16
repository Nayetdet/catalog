package io.github.nayetdet.catalog.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(name = "Category")
public class CategoryDTO {

    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

}
