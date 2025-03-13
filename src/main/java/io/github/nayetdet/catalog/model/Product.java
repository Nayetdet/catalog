package io.github.nayetdet.catalog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table
public class Product extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 300)
    private String description;

    @Column(precision = 18, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
