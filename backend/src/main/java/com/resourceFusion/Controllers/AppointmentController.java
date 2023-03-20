package com.resourceFusion.Controllers;

import com.resourceFusion.helpers.dto.AppointmentRequest;
import com.resourceFusion.models.Appointment;
import com.resourceFusion.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    @PostMapping("create")
    public Appointment makeAppointment (@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.
                makeAppointment(appointmentRequest.scheduleId(), appointmentRequest.localDate());
    }

    @DeleteMapping("cancel/{appointmentId}")
    public void cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.
                cancelAppointment(appointmentId);
    }
}
