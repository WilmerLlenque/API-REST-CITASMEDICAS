package com.citasmedicas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
@IdClass(value = PatientPK.class)
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    //ATRIBUTOS
    @Id
    @Column(name = "id_patient",nullable = false)
    private Integer idPatient;

    @Id
    @Column(name = "id_user",nullable = false)
    private Integer idUser;

    @Column(nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime birthday;

    @Column(nullable = false,columnDefinition = "DECIMAL(6,2)")
    private Double whight;

    @Column(nullable = false,columnDefinition = "DECIMAL(6,2)")
    private Double height;

    @Column(nullable = false,length = 45)
    private String blood;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private boolean available;

    //RELACIONES
    @OneToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id_user",insertable = false,updatable = false)
    @MapsId
    private User user;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<AppointmentDoctor> appointmentDoctors;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<AppointmentNurse> appointmentNurses;


}
