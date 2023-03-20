package com.appointee.models.geo;

import com.appointee.models.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City extends BaseEntity<Integer> {
    private String name;
}
