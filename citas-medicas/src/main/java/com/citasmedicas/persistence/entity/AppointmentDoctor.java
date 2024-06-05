package com.citasmedicas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_doctor")
@Getter
@Setter
@NoArgsConstructor
public class AppointmentDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment_doctor",nullable = false)
    private Integer idAppointmentDoctor;

    @Column(nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false,length = 45)
    private String time;

    @Column(name = "id_user_patient",nullable = false)
    private Integer idUserPatient;

    @Column(name = "id_patient",nullable = false)
    private Integer idPatient;

    @Column(name = "id_user_doctor",nullable = false)
    private Integer idUserDoctor;

    @Column(name = "id_doctor",nullable = false)
    private Integer idDocotor;

    //RELACIONES

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_patient",referencedColumnName = "id_patient",insertable = false,updatable = false),
            @JoinColumn(name = "id_user_patient",referencedColumnName = "id_user",insertable = false,updatable = false)
    })
    private Patient patient;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_doctor",referencedColumnName = "id_doctor",insertable = false,updatable = false),
            @JoinColumn(name = "id_user_doctor",referencedColumnName = "id_user",insertable = false,updatable = false)
    })
    private Doctor doctor;
}
