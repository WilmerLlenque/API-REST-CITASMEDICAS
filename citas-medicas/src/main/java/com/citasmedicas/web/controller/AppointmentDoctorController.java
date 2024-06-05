package com.citasmedicas.web.controller;

import com.citasmedicas.persistence.entity.*;
import com.citasmedicas.services.AppointmentDoctorService;
import com.citasmedicas.services.DoctorService;
import com.citasmedicas.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/appoinmentDoctor")
public class AppointmentDoctorController {

    @Autowired
    private AppointmentDoctorService appointmentDoctorService;
    @Autowired
    private PatientServices patientServices;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("all")
    public List<AppointmentDoctor> getAppointmentDoctor(){
        return appointmentDoctorService.getAppointmentDoctor();
    }

    @GetMapping("/{id}")
    public AppointmentDoctor getAppointmentDoctorId(@PathVariable int id){
        return appointmentDoctorService.getAppointmentDoctorId(id);
    }

    @PostMapping
    public ResponseEntity<AppointmentDoctor> agendarCita(@RequestBody AppointmentDoctor appointmentDoctor){
        if (appointmentDoctor.getIdAppointmentDoctor() == null || !appointmentDoctorService.existsById(appointmentDoctor.getIdAppointmentDoctor())){
            Patient patient= patientServices.findPatientById(new PatientPK(appointmentDoctor.getIdPatient(),appointmentDoctor.getIdUserPatient()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacio"));
            Doctor doctor=doctorService.findDoctorById(new DoctorPK(appointmentDoctor.getIdDocotor(),appointmentDoctor.getIdUserDoctor()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacion"));
            appointmentDoctor.setDoctor(doctor);
            appointmentDoctor.setPatient(patient);

            return ResponseEntity.ok(appointmentDoctorService.guardarCita(appointmentDoctor));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<AppointmentDoctor> updateCita(@RequestBody AppointmentDoctor appointmentDoctor){
        if (appointmentDoctor.getIdAppointmentDoctor() != null && appointmentDoctorService.existsById(appointmentDoctor.getIdAppointmentDoctor())){
            Patient patient= patientServices.findPatientById(new PatientPK(appointmentDoctor.getIdPatient(),appointmentDoctor.getIdUserPatient()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacio"));
            Doctor doctor=doctorService.findDoctorById(new DoctorPK(appointmentDoctor.getIdDocotor(),appointmentDoctor.getIdUserDoctor()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacion"));
            appointmentDoctor.setDoctor(doctor);
            appointmentDoctor.setPatient(patient);

            return ResponseEntity.ok(appointmentDoctorService.guardarCita(appointmentDoctor));
        }
        return ResponseEntity.badRequest().build();
    }

}
