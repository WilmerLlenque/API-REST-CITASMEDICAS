package com.citasmedicas.web.controller;

import com.citasmedicas.persistence.entity.*;
import com.citasmedicas.services.AppointmentNurseService;
import com.citasmedicas.services.DoctorService;
import com.citasmedicas.services.NurseService;
import com.citasmedicas.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/appointmentNurse")
public class AppointmentNurseController {

    @Autowired
    private AppointmentNurseService appointmentNurseService;
    @Autowired
    private PatientServices patientServices;
    @Autowired
    private NurseService nurseService;

    @GetMapping("all")
    public List<AppointmentNurse> getAppointmentNurse(){
        return appointmentNurseService.getAppointmentNurse();
    }

    @GetMapping("/{id}")
    public AppointmentNurse getAppointmentNurseId(@PathVariable int id){
        return appointmentNurseService.getAppointmentNurseId(id);
    }

    @PostMapping
    public ResponseEntity<AppointmentNurse> agendarCita(@RequestBody AppointmentNurse appointmentNurse){
        if (appointmentNurse.getIdAppointmentNurse() == null || !appointmentNurseService.existsById(appointmentNurse.getIdAppointmentNurse())){
            Patient patient= patientServices.findPatientById(new PatientPK(appointmentNurse.getIdPatient(),appointmentNurse.getIdUserPatient()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacio"));
            Nurse nurse=nurseService.findNurseById(new NursePK(appointmentNurse.getIdNurse(),appointmentNurse.getIdUserNurse()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacion"));
            appointmentNurse.setNurse(nurse);
            appointmentNurse.setPatient(patient);

            return ResponseEntity.ok(appointmentNurseService.guardarCita(appointmentNurse));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<AppointmentNurse> updateCita(@RequestBody AppointmentNurse appointmentNurse){
        if (appointmentNurse.getIdAppointmentNurse() != null && appointmentNurseService.existsById(appointmentNurse.getIdAppointmentNurse())){
            Patient patient= patientServices.findPatientById(new PatientPK(appointmentNurse.getIdPatient(),appointmentNurse.getIdUserPatient()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacio"));
            Nurse nurse=nurseService.findNurseById(new NursePK(appointmentNurse.getIdNurse(),appointmentNurse.getIdUserNurse()))
                    .orElseThrow(()->new NoSuchElementException("El optional esta vacion"));
            appointmentNurse.setNurse(nurse);
            appointmentNurse.setPatient(patient);

            return ResponseEntity.ok(appointmentNurseService.guardarCita(appointmentNurse));
        }
        return ResponseEntity.badRequest().build();
    }
}
