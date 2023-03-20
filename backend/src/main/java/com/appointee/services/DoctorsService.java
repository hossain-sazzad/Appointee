package com.appointee.services;

import com.appointee.models.Doctor;
import com.appointee.models.Specialty;
import com.appointee.models.geo.City;
import com.appointee.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorsService {
    private final DoctorRepository doctorRepository;

    // TODO
    // Add test
    public Page<Doctor> fetchDoctorsUsingFilter(
            Optional<City> city,
            Optional<Specialty> specialty,
            Optional<DayOfWeek> dayOfWeek,
            Optional<String> searchKey,
            Pageable pageable
    ){
        return doctorRepository.findDoctors(city, specialty, dayOfWeek, searchKey, pageable);
    }
}
