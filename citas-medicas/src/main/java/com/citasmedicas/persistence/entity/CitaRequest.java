package com.citasmedicas.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CitaRequest {

    private LocalDateTime date;
    private String time;
    private Integer idUserPatient;
    private Integer idPatient;
    private Integer idUserDoctor;
    private Integer idDocotor;
}
