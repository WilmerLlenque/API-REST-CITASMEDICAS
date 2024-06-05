package com.citasmedicas.persistence.repository;

import com.citasmedicas.persistence.entity.AppointmentDoctor;
import com.citasmedicas.persistence.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface AppointmetDoctorRepository extends ListCrudRepository<AppointmentDoctor,Integer> {

}
