package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.repository.model.Clinic;

import java.util.List;

public interface ClinicService {
    List<Clinic> GetAllDeclarations();
//    List<Clinic> GetByDoctorId(long idDoctor) throws IllegalArgumentException;
//    Clinic GetByPatientId(long idPatient) throws IllegalArgumentException;
    long createDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException;
    void updateDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException;
    void deleteDeclaration(long idPatient);
}
