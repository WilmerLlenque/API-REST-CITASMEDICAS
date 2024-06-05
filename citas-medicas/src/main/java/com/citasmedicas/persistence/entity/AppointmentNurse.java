package com.citasmedicas.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_nurse")
@Getter
@Setter
@NoArgsConstructor
public class AppointmentNurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment_nurse",nullable = false)
    private Integer idAppointmentNurse;

    @Column(nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false,length = 45)
    private String time;

    @Column(name = "id_user_patient", nullable = false)
    private Integer idUserPatient;

    @Column(name = "id_patient", nullable = false)
    private Integer idPatient;

    @Column(name = "id_user_nurse", nullable = false)
    private Integer idUserNurse;

    @Column(name = "id_nurse",nullable = false)
    private Integer idNurse;

    //RELACIONES
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_patient", columnDefinition = "id_patient",insertable = false,updatable = false),
            @JoinColumn(name = "id_user_patient", columnDefinition = "id_user",insertable = false,updatable = false)
    })
    private Patient patient;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_nurse",columnDefinition = "id_nurse",insertable = false,updatable = false),
            @JoinColumn(name = "id_user_nurse",columnDefinition = "id_user",insertable = false,updatable = false)
    })
    private Nurse nurse;

}
