package com.resourceFusion.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chamber extends BaseEntity<Long> {
    @NotBlank
    private String address;

}
