package com.appointee.helpers.dto;

import com.appointee.models.Doctor;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Data
public class DoctorsSchedule {
    Doctor doctor;
    Map<DayOfWeek, List<ChamberTime>> chamberDetailsByWeekDay;
}
