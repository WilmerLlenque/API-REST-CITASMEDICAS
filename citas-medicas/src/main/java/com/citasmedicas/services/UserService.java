package com.citasmedicas.services;

import com.citasmedicas.persistence.entity.Patient;
import com.citasmedicas.persistence.entity.User;
import com.citasmedicas.persistence.repository.PatientRepository;
import com.citasmedicas.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public long countUser(){
        return userRepository.count()+1;
    }
    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
