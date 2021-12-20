package edu.kpi.iasa.clinic.service.impl;


import edu.kpi.iasa.clinic.exception.DeclarationNotFoundException;
import edu.kpi.iasa.clinic.exception.UserNotFoundException;
import edu.kpi.iasa.clinic.repository.ClinicRepository;

import edu.kpi.iasa.clinic.repository.UserRepository;
import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.repository.model.Clinic;
import edu.kpi.iasa.clinic.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RequiredArgsConstructor
@Service
public class ClinicServiceImpl implements ClinicService {

    final private ClinicRepository clinicRepository;

    @Override
    public List<Clinic> GetAllDeclarations() {
        return clinicRepository.findAll();
    }

    @Override
    public List<String> GetDoctors(){
        return clinicRepository.getAllDoctors();
    }

    @Override
    public List<Clinic> GetByIdDoctor(long idDoctor) throws IllegalArgumentException{
        return clinicRepository.findAllByIdDoctor(idDoctor);
    }

    @Override
    public long createDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException{
        Clinic declaration = new Clinic(idPatient, idDoctor);
        Clinic savedDeclaration = clinicRepository.save(declaration);

        return savedDeclaration.getIdPatient();
    }

    @Override
    public void updateDeclaration(long idPatient, long idDoctor){
        Clinic declaration = clinicRepository.findById(idPatient).orElseThrow(DeclarationNotFoundException::new);
        declaration.setIdDoctor(idDoctor);
        clinicRepository.save(declaration);
    }

    @Override
    public void deleteDeclaration(long idPatient) {
        clinicRepository.deleteById(idPatient);
    }
}
