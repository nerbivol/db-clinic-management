package edu.kpi.iasa.clinic.service.impl;


import edu.kpi.iasa.clinic.repository.ClinicRepository;

import edu.kpi.iasa.clinic.repository.model.Clinic;
import edu.kpi.iasa.clinic.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClinicServiceImpl implements ClinicService {

    final private ClinicRepository clinicRepository;

    @Override
    public List<Clinic> GetAllDeclarations() {
        return clinicRepository.findAll();
    }

//    @Override
//    public List<Clinic> GetByDoctorId(long idDoctor) throws IllegalArgumentException{
//        return clinicRepo.findAllByDoctor(idDoctor);
//    }
//
//    @Override
//    public Clinic GetByPatientId(long idPatient) throws IllegalArgumentException {
//        return clinicRepo.findByPatient(idPatient);
//    }

    @Override
    public long createDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException{
        Clinic declaration = new Clinic(idPatient, idDoctor);
        Clinic savedDeclaration = clinicRepository.save(declaration);

        return savedDeclaration.getIdPatient();
    }

    @Override
    public void updateDeclaration(long idPatient, long idDoctor) throws IllegalArgumentException {

    }

    @Override
    public void deleteDeclaration(long idPatient) {

    }

}
