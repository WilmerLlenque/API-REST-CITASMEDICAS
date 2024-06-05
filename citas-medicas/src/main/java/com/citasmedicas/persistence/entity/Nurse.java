package com.citasmedicas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "nurse")
@IdClass(NursePK.class)
@Getter
@Setter
@NoArgsConstructor
public class Nurse{

    @Id
    @Column(name = "id_nurse",nullable = false)
    private Integer idNurse;

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

    @OneToMany(mappedBy = "nurse")
    @JsonIgnore
    private List<AppointmentNurse> appointmentNurses;

}
