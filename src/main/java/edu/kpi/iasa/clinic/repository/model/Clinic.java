package edu.kpi.iasa.clinic.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Table(name = "declarations")
public class Clinic {

    @Id
    private long idPatient;
    private long idDoctor;

    public Clinic(){}

    public Clinic(long idPatient, long idDoctor){
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
    }

    @Column(name = "id_patient")
    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    @Column(name = "id_doctor")
    public long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(long idDoctor) {
        this.idDoctor = idDoctor;
    }
}
