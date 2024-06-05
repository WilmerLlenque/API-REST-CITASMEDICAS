package com.citasmedicas.persistence.repository;

import com.citasmedicas.persistence.entity.Nurse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface NurseRepository extends ListCrudRepository<Nurse,Integer> {

    @Query(value = "SELECT * FROM nurse n where n.id_user= :idUser and n.id_nurse= :idNurse",nativeQuery = true)
    Optional<Nurse> getNurse(int idUser, int idNurse);
}
