package com.citasmedicas.persistence.repository;

import com.citasmedicas.persistence.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface DoctorRepository extends ListCrudRepository<Doctor,Integer> {

    @Query(value = "SELECT * FROM doctor d where d.id_user= :idUser and d.id_doctor= :idDoc",nativeQuery = true)
    Optional<Doctor> getDoctor(int idUser, int idDoc);
}
