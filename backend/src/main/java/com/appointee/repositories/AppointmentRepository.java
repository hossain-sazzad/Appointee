package com.appointee.repositories;

import com.appointee.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select MAX (app.serialNo) from Appointment app " +
            "where app.schedule.id = :scheduleId and app.appointmentDate = :appointmentDate ")
    public Integer findLatestAppointmentSerial(Long scheduleId, LocalDate appointmentDate);

    @Query("select app.serialNo from Appointment app " +
            "where app.schedule.id = :scheduleId and app.appointmentDate = :appointmentDate and " +
            "app.appointmentStatus = com.appointee.models.AppointmentStatus.APPOINTED")
    public List<Integer> fetchAllAppointedSerial(Long scheduleId, LocalDate appointmentDate);
}
