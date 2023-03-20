package com.appointee.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Specialty extends BaseEntity<Integer> {
    @NotBlank
    private String name;
}
