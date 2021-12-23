package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.repository.model.Clinic;

import java.util.List;

public interface ClinicService {
    List<Clinic> getAllDiagnostic();
    Clinic getDiagnosticById(long id);
    long createDiagnostic(Clinic clinic);
    void updateDiagnostic(long id, long idPatient, String complains,
                          String conclusion, String diagnose, double price,
                          String additionalReview);
    void deleteDiagnostic(long id);
}
