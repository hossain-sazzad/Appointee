package com.resourceFusion.helpers.dto;

import java.time.LocalDate;

public record AppointmentRequest (
    Long scheduleId,
    LocalDate localDate
){}
