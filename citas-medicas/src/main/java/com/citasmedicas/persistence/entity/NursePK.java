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

public class NursePK implements Serializable {

    private Integer idNurse;
    private Integer idUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NursePK nursePK)) return false;
        return Objects.equals(idNurse, nursePK.idNurse) && Objects.equals(idUser, nursePK.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNurse, idUser);
    }
}
