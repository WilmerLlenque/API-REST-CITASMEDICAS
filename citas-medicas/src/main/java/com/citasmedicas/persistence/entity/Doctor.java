package com.citasmedicas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "doctor")
@IdClass(DoctorPK.class)
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    @Id
    @Column(name = "id_doctor",nullable = false)
    private Integer idDoctor;

    @Id
    @Column(name = "id_user",nullable = false)
    private Integer idUser;

    @Column(nullable = false,length = 100)
    private String speciality;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private boolean available;

    //RELACIONES
    @OneToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id_user",insertable = false,updatable = false)
    @MapsId
    private User user;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<AppointmentDoctor> appointmentDoctors=new ArrayList<>();

    //CITAS DISPONIBLES
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    public List<AppointmentAvailable> appointmentAvailables=new ArrayList<>();

}
