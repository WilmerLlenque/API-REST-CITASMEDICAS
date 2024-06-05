package com.citasmedicas.services;

import com.citasmedicas.persistence.entity.Doctor;
import com.citasmedicas.persistence.entity.DoctorPK;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.persistence.repository.DoctorRepository;

import com.citasmedicas.persistence.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Doctor> getDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorId(int idUser,int idDoc){
        return doctorRepository.getDoctor(idUser,idDoc);
    }

    public Doctor getDoctorById(Integer idUser,Integer idDoc) {
        return doctorRepository.getDoctor(idUser,idDoc).orElseThrow(()-> new RuntimeException("Valor no presente"));
    }
    public Optional<Doctor> findDoctorById(DoctorPK doctorPK){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(entityManager.find(Doctor.class,doctorPK));
    }

    public Doctor saveUserWithDoctor(User user, Doctor doctor){
        userRepository.save(user);
        doctor.setUser(user);
        doctorRepository.save(doctor);
        return doctor;
    }
    public long countDoctor(){
        return doctorRepository.count();
    }

    public void delete(Doctor doctor){
        doctorRepository.save(doctor);
    }

}
