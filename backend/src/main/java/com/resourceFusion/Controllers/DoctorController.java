package com.resourceFusion.Controllers;

import com.resourceFusion.models.Doctor;
import com.resourceFusion.models.Specialty;
import com.resourceFusion.models.geo.City;
import com.resourceFusion.services.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorsService doctorsService;

    @GetMapping("filter")
    public Page<Doctor> fetchDoctorsByFilter(
            @RequestParam Optional<City> city,
            @RequestParam Optional<Specialty> specialty,
            @RequestParam Optional<DayOfWeek> dayOfWeek,
            @RequestParam Optional<String> searchKey,
            Pageable pageable
    ) {
        return doctorsService.fetchDoctorsUsingFilter(city, specialty, dayOfWeek, searchKey, pageable);
    }
}
