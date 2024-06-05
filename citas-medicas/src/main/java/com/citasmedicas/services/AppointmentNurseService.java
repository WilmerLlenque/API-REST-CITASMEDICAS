package com.citasmedicas.services;

import com.citasmedicas.persistence.entity.AppointmentDoctor;
import com.citasmedicas.persistence.entity.AppointmentNurse;
import com.citasmedicas.persistence.repository.AppointmentNurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentNurseService {

    @Autowired
    private AppointmentNurseRepository appointmentNurseRepository;

    public List<AppointmentNurse> getAppointmentNurse(){
        return appointmentNurseRepository.findAll();
    }

    public AppointmentNurse getAppointmentNurseId(int id){
        return appointmentNurseRepository.findById(id).orElseThrow(
                ()->new RuntimeException("El optional esta vacio"));
    }

    public AppointmentNurse guardarCita(AppointmentNurse appointmentNurse){
        return appointmentNurseRepository.save(appointmentNurse);
    }
    public boolean existsById(int id){
        return appointmentNurseRepository.existsById(id);
    }
}
