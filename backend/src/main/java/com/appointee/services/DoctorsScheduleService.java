package com.appointee.services;

import com.appointee.helpers.dto.ChamberTime;
import com.appointee.helpers.dto.DoctorsSchedule;
import com.appointee.models.Doctor;
import com.appointee.models.Schedule;
import com.appointee.repositories.DoctorRepository;
import com.appointee.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorsScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    // TODO
    // Add test
    public DoctorsSchedule fetchDoctorsSchedule (Long doctorId) {
        List<Schedule> schedules =
                scheduleRepository.findByDoctor_IdAndIsActive(doctorId, Boolean.TRUE);
        Doctor doctor = doctorRepository.findById(doctorId).
                orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor id is not correct")
                );
        DoctorsSchedule doctorsSchedule = new DoctorsSchedule();
        doctorsSchedule.setDoctor(doctor);
        Map<DayOfWeek, List<ChamberTime>> chamberDetailsByWeekDay = new HashMap<>();
        for (Schedule schedule : schedules) {
            chamberDetailsByWeekDay.putIfAbsent(schedule.getDayOfWeek(), new ArrayList<>());
            List<ChamberTime> chamberTimes = chamberDetailsByWeekDay.get(schedule.getDayOfWeek());
            chamberTimes.add(
                    new ChamberTime(schedule.getChamber(), schedule.getStartingTime(), schedule.getEndingTime())
            );
        }
        doctorsSchedule.setChamberDetailsByWeekDay(chamberDetailsByWeekDay);
        return doctorsSchedule;
    }
}
