package com.appointee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Appointment extends BaseEntity<Long>{
    @OneToOne
    private Client CLient;
    @OneToOne
    private Schedule schedule;

    private LocalDate appointmentDate;

    private Integer serialNo;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;
}
