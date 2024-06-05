package com.citasmedicas.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id_user",nullable = false)
    private Integer idUser;

    @Column(nullable = false,length = 45)
    private String name;

    @Column(nullable = false,length = 65,unique = true)
    private String email;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(name = "phone_number",length = 45)
    private String phoneNumber;

}
