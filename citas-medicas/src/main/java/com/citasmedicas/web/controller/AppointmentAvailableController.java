package com.citasmedicas.web.controller;

import com.citasmedicas.persistence.entity.AppointmentAvailable;
import com.citasmedicas.services.AppointmentAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availableAppointments")
public class AppointmentAvailableController {

    @Autowired
    private AppointmentAvailableService appointmentAvailableService;

    @GetMapping("/{idUser}/{idDoctor}")
    public List<AppointmentAvailable> getAvailableAppointments(@PathVariable Integer idUser,
                                                               @PathVariable Integer idDoctor){
        return appointmentAvailableService.getAvailableAppointments(idUser,idDoctor);
    }

    @PostMapping("/book/{appointmentId}")
    public AppointmentAvailable bookAppointment(@PathVariable Integer appointmentId) {
        return appointmentAvailableService.bookAppointment(appointmentId);
    }

    @PostMapping("/save")
    public AppointmentAvailable saveAppointmentsAvailable(@RequestBody AppointmentAvailable appointmentAvailable){
        return appointmentAvailableService.save(appointmentAvailable);
    }

    @PutMapping("/update")
    public AppointmentAvailable updateAppointmentAvailable(@RequestBody AppointmentAvailable appointmentAvailable){
        return appointmentAvailableService.save(appointmentAvailable);
    }


}
