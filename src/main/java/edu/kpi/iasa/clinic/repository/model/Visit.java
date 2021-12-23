package edu.kpi.iasa.clinic.repository.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Table(name = "visits_list")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_patient")
    @NotNull(message = "validation.text.error.required.field")
    private long idPatient;

    @Column(name = "date", columnDefinition = "Date")
    @NotNull(message = "validation.text.error.required.field")
    private LocalDate dateVisit;

    @Column(name = "time", columnDefinition = "Time")
    @NotNull(message = "validation.text.error.required.field")
    private LocalTime timeVisit;

    public Visit(){ }

    public Visit(long idPatient, LocalDate dateVisit, LocalTime timeVisit) {
        this.idPatient = idPatient;
        this.dateVisit = dateVisit;
        this.timeVisit = timeVisit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public LocalDate getDateVisit() {
        return dateVisit;
    }


    public void setDateVisit(LocalDate dateVisit) {
        this.dateVisit = dateVisit;
    }

    public LocalTime getTimeVisit() {
        return timeVisit;
    }

    public void setTimeVisit(LocalTime timeVisit) {
        this.timeVisit = timeVisit;
    }
}
