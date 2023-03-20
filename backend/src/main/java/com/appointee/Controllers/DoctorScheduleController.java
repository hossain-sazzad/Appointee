package com.appointee.Controllers;

import com.appointee.helpers.dto.DoctorsSchedule;
import com.appointee.services.DoctorsScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/schedule")
@RequiredArgsConstructor
public class DoctorScheduleController {
    private final DoctorsScheduleService doctorsScheduleService;
    @GetMapping("")
    public DoctorsSchedule fetchDoctorsSchedule (@RequestParam Long doctorId) {
        return doctorsScheduleService.fetchDoctorsSchedule(doctorId);
    }
}
