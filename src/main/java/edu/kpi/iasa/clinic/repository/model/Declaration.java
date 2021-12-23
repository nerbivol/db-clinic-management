package edu.kpi.iasa.clinic.repository.model;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Table(name = "declarations")
public class Declaration {

    @Id
    private long idPatient;
    private long idDoctor;

    public Declaration(){}

    public Declaration(long idPatient, long idDoctor){
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
