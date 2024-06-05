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
public class DoctorPK implements Serializable {

    private Integer idDoctor;
    private Integer idUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorPK doctorPK)) return false;
        return Objects.equals(idDoctor, doctorPK.idDoctor) && Objects.equals(idUser, doctorPK.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoctor, idUser);
    }
}