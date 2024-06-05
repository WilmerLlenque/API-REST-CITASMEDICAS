package com.citasmedicas.persistence.repository;

import com.citasmedicas.persistence.entity.Nurse;
import com.citasmedicas.persistence.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface PatientRepository extends ListCrudRepository<Patient,Integer> {

    @Query(value = "SELECT * FROM patient p where p.id_user= :idUser and p.id_patient= :idPatient",nativeQuery = true)
    Optional<Patient> getPatient(int idUser, int idPatient);
}
