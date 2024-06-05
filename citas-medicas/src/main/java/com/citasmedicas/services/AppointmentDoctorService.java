package com.citasmedicas.services;

import com.citasmedicas.persistence.entity.AppointmentDoctor;
import com.citasmedicas.persistence.repository.AppointmetDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentDoctorService {

    @Autowired
    private AppointmetDoctorRepository appointmetDoctorRepository;

    public List<AppointmentDoctor> getAppointmentDoctor(){
        return appointmetDoctorRepository.findAll();
    }

    public AppointmentDoctor getAppointmentDoctorId(int id){
        return appointmetDoctorRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("El optional esta vacio"));
    }

    public AppointmentDoctor guardarCita(AppointmentDoctor appointmentDoctor){
        return appointmetDoctorRepository.save(appointmentDoctor);
    }
    public boolean existsById(int id){
        return appointmetDoctorRepository.existsById(id);
    }

}
