package com.citasmedicas.services;


import com.citasmedicas.persistence.entity.DoctorPK;
import com.citasmedicas.persistence.entity.Nurse;
import com.citasmedicas.persistence.entity.NursePK;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.persistence.repository.NurseRepository;
import com.citasmedicas.persistence.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Nurse> getNurses(){
        return nurseRepository.findAll();
    }

    public Optional<Nurse> getNurseId(int idUser, int idNurse){
        return nurseRepository.getNurse(idUser,idNurse);
    }

    public Optional<Nurse> findNurseById(NursePK nursePK){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        return Optional.ofNullable(entityManager.find(Nurse.class,nursePK));
    }
    public Nurse saveUserWithNurse(User user, Nurse nurse){
        userRepository.save(user);
        nurse.setUser(user);
        nurseRepository.save(nurse);
        return nurse;
    }

    public long countNurse(){
        return nurseRepository.count();
    }

    public void delete(Nurse nurse){
        nurseRepository.save(nurse);
    }
}
