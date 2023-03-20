package com.resourceFusion.repositories;

import com.resourceFusion.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select MAX (app.serialNo) from Appointment app " +
            "where app.schedule.id = :scheduleId and app.appointmentDate = :appointmentDate ")
    public Integer findLatestAppointmentSerial(Long scheduleId, LocalDate appointmentDate);

    @Query("select app.serialNo from Appointment app " +
            "where app.schedule.id = :scheduleId and app.appointmentDate = :appointmentDate and " +
            "app.appointmentStatus = com.resourceFusion.models.AppointmentStatus.CANCELLED limit 1")
    public Optional<Integer> findCancelledAppointmentSerialIfAny(Long scheduleId, LocalDate appointmentDate);

    @Query("select app.serialNo from Appointment app " +
            "where app.schedule.id = :scheduleId and app.appointmentDate = :appointmentDate and " +
            "app.appointmentStatus = com.resourceFusion.models.AppointmentStatus.APPOINTED")
    public List<Integer> fetchAllAppointedSerial(Long scheduleId, LocalDate appointmentDate);
}
