package io.github.nayetdet.catalog.utils.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Schema(name = "Page")
public class CustomPage<T> {

    private List<T> content;
    private CustomPageable pageable;

    public CustomPage(Page<T> page) {
        content = page.getContent();
        pageable = new CustomPageable(
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalElements()
        );
    }

}
