package io.github.nayetdet.catalog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table
public class Permission extends AbstractEntity implements GrantedAuthority {

    @Column(nullable = false)
    private String description;

    @Override
    public String getAuthority() {
        return description;
    }

}
