package com.citasmedicas.services;

import com.citasmedicas.persistence.entity.Patient;
import com.citasmedicas.persistence.entity.PatientPK;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.persistence.repository.PatientRepository;
import com.citasmedicas.persistence.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Patient> getPatient(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientId(int idUser, int idPatient){
        return patientRepository.getPatient(idUser,idPatient);
    }


    public Optional<Patient> findPatientById(PatientPK patientPK){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        return Optional.ofNullable(entityManager.find(Patient.class,patientPK));
    }
    public User saveUserWithPatient(User user, Patient patient){
        userRepository.save(user);
        patient.setUser(user);
        patientRepository.save(patient);

        return user;
    }

    public long countPatient(){
        return patientRepository.count();
    }

    public void delete(Patient patient){
        patientRepository.save(patient);
    }

}
