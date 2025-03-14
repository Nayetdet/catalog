package io.github.nayetdet.catalog.dto.category;

import io.github.nayetdet.catalog.utils.page.AbstractCustomPagedSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CategorySearch")
public class CategorySearchDTO extends AbstractCustomPagedSearch {

    private String name;

}
