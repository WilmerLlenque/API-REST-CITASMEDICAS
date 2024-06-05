package com.citasmedicas.services;

import com.citasmedicas.persistence.entity.AppointmentAvailable;
import com.citasmedicas.persistence.entity.Doctor;
import com.citasmedicas.persistence.repository.AppointmentAvailableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentAvailableService {

    @Autowired
    private AppointmentAvailableRepository appointmentAvailableRepository;
    @Autowired
    private DoctorService doctorService;

    public List<AppointmentAvailable> getAvailableAppointments(Integer userId,Integer doctorId){
        Doctor doctor=doctorService.getDoctorById(userId,doctorId);
        return appointmentAvailableRepository.findByDoctorAndIsReservadoFalse(doctor);
    }

    public AppointmentAvailable bookAppointment(Integer appointmentId) {
        AppointmentAvailable appointment = appointmentAvailableRepository.findById(appointmentId).orElse(null);
        if (appointment != null && !appointment.isReservado()) {
            appointment.setReservado(true);
            return appointmentAvailableRepository.save(appointment);
        }
        return null;
    }

    public AppointmentAvailable save(AppointmentAvailable appointmentAvailable){
        return appointmentAvailableRepository.save(appointmentAvailable);
    }
}
