package com.appointee.models;

import com.appointee.models.geo.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Doctor extends BaseEntity<Long> {
    @NotBlank
    private String name;
    @NotBlank
    private String designation;
    private Integer fee;
    @OneToMany
    @JoinColumn(name = "city_id")
    private List<City> serviceArea;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id")
    private List<Specialty> specialties;
}
