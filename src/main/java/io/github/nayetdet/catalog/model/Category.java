package io.github.nayetdet.catalog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Category extends AbstractEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
