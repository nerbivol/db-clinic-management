package edu.kpi.iasa.clinic.service.impl;


import edu.kpi.iasa.clinic.repository.DeclarationRepository;

import edu.kpi.iasa.clinic.repository.model.Declaration;
import edu.kpi.iasa.clinic.service.DeclarationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeclarationServiceImpl implements DeclarationService {

    final private DeclarationRepository declarationRepository;

    @Override
    public List<Declaration> GetAllDeclarations() {
        return declarationRepository.findAll();
    }

    @Override
    public List<Declaration> GetByDoctorId(long idDoctor) throws IllegalArgumentException{
        return declarationRepository.findAllByIdDoctor(idDoctor);
    }

    @Override
    public Declaration GetByIdPatient(long idPatient) throws IllegalArgumentException {
        return declarationRepository.findByIdPatient(idPatient);
    }

    @Override
    public long createDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException{
        Declaration declaration = new Declaration(idPatient, idDoctor);
        Declaration savedDeclaration = declarationRepository.save(declaration);

        return savedDeclaration.getIdPatient();
    }

    @Override
    public void deleteDeclaration(long idPatient) {
        declarationRepository.deleteById(idPatient);
    }
}
