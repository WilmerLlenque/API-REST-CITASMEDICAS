package com.citasmedicas.persistence.repository;

import com.citasmedicas.persistence.entity.Nurse;
import com.citasmedicas.persistence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User,Integer> {

    User findByEmail(String email);
}
