package com.appointee.services;

import com.appointee.helpers.utils.Common;
import com.appointee.models.Appointment;
import com.appointee.models.AppointmentStatus;
import com.appointee.models.Schedule;
import com.appointee.repositories.AppointmentRepository;
import com.appointee.repositories.DoctorRepository;
import com.appointee.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;
    private final AppointmentRepository appointmentRepository;

    // TODO
    // Add test
    public Appointment makeAppointment(Long scheduleId, LocalDate appointmentDate) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule not found with this id")
        );
        if (!schedule.getIsActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor is not available in this date");
        }

        Integer serial;
        List<Integer> allAppointedSerial = appointmentRepository.
                fetchAllAppointedSerial(scheduleId, appointmentDate);
        if (allAppointedSerial.size() == schedule.getTotalPatients()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor's all appointment is booked");
        } else if (allAppointedSerial.size() == 0) {
            serial = 1;
        } else {
            serial = Common.findSmallestAbsentNumber(allAppointedSerial);
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);
        appointment.setSchedule(schedule);
        appointment.setSerialNo(serial);
        appointment.setAppointmentStatus(AppointmentStatus.APPOINTED);
        appointmentRepository.save(appointment);
        return appointment;
    }

    // TODO
    // Add test
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.
                findById (appointmentId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No appointment found with this id"));
        // TODO
        // Check if authorized person
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
    }
}
