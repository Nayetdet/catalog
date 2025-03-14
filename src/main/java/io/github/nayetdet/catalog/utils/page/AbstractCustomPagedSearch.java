package io.github.nayetdet.catalog.utils.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCustomPagedSearch {

    @Schema(defaultValue = "0")
    protected Integer pageNumber = 0;

    @Schema(defaultValue = "10")
    protected Integer pageSize = 10;

}
