package com.appointee.Controllers;

import com.appointee.helpers.dto.AppointmentRequest;
import com.appointee.models.Appointment;
import com.appointee.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
