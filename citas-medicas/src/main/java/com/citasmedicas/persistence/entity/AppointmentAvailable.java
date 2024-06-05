package com.citasmedicas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="appointment_available")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AppointmentAvailable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_AAppointment",nullable = false)
    private Integer idAvailableAppointment;

    @Column(nullable = false,columnDefinition = "DATE")
    @NonNull private Date date;

    @Column(nullable = false,length = 45)
    @NonNull private String time;

    @Column(name = "id_user_doctor",nullable = false)
    private Integer idUserDoctor;

    @Column(name = "id_doctor",nullable = false)
    private Integer idDoctor;

    @Column(name = "is_reservado",nullable = false,columnDefinition = "TINYINT")
    private boolean isReservado;

    @ManyToOne
    @JsonIgnore
    @JoinColumns({
            @JoinColumn(name = "id_doctor",referencedColumnName = "id_doctor",insertable = false,updatable = false),
            @JoinColumn(name = "id_user_doctor",referencedColumnName = "id_user",insertable = false,updatable = false)
    })
    private Doctor doctor;

}
