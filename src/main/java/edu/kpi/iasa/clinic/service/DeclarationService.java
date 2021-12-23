package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.repository.model.Declaration;

import java.util.List;

public interface DeclarationService {
    List<Declaration> GetAllDeclarations();
    List<Declaration> GetByDoctorId(long idDoctor) throws IllegalArgumentException;
    Declaration GetByIdPatient(long idPatient) throws IllegalArgumentException;
    long createDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException;
    void deleteDeclaration(long idPatient);
}
