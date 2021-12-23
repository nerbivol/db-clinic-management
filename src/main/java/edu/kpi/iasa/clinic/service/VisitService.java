package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.repository.model.Visit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface VisitService {
    List<Visit> getAllVisits();
    Visit getVisitById(long id);
    long createVisit(long idPatient, LocalDate dateVisit, LocalTime timeVisit);
    void deleteVisit(long id);
}
