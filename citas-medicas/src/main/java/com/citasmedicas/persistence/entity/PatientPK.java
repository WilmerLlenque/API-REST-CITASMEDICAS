package com.citasmedicas.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PatientPK implements Serializable {

    private Integer idPatient;
    private Integer idUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientPK patientPK)) return false;
        return Objects.equals(idPatient, patientPK.idPatient) && Objects.equals(idUser, patientPK.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPatient, idUser);
    }
}
