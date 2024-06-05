package com.citasmedicas.persistence.repository;

import com.citasmedicas.persistence.entity.AppointmentAvailable;
import com.citasmedicas.persistence.entity.Doctor;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AppointmentAvailableRepository extends ListCrudRepository<AppointmentAvailable,Integer> {

    List<AppointmentAvailable> findByDoctorAndIsReservadoFalse(Doctor doctor);
}
