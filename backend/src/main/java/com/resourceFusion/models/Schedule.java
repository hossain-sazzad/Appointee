package com.resourceFusion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Schedule extends BaseEntity<Long> {

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private Chamber chamber;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalDateTime startingTime;

    private LocalDateTime endingTime;

    private Integer totalPatients;

    private Boolean isActive;

}
