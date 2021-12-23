package edu.kpi.iasa.clinic.service.impl;

import edu.kpi.iasa.clinic.exception.PatientEqualDoctorException;
import edu.kpi.iasa.clinic.exception.PatientNotDoctorException;
import edu.kpi.iasa.clinic.repository.DeclarationRepository;

import edu.kpi.iasa.clinic.repository.RoleRepository;
import edu.kpi.iasa.clinic.repository.model.Declaration;
import edu.kpi.iasa.clinic.service.DeclarationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeclarationServiceImpl implements DeclarationService {

    final private DeclarationRepository declarationRepository;
    final private RoleRepository roleRepository;

    @Override
    public List<Declaration> GetAllDeclarations() {
        return declarationRepository.findAll();
    }

    @Override
    public List<String> GetAllDoctor() {
        return declarationRepository.getAllDoctors();
    }

    @Override
    public List<Declaration> GetDoctorPatient() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        long id = declarationRepository.getIdFromEmail(email);

        return declarationRepository.findAllByIdDoctor(id);
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
    public long createDeclaration(long idDoctor){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        long idPatient = declarationRepository.getIdFromEmail(email);
        long code = declarationRepository.getRoleById(idDoctor);
        if(code!=4) throw new PatientNotDoctorException();
        if(idPatient != idDoctor) {
            Declaration declaration = new Declaration(idPatient, idDoctor);
            Declaration savedDeclaration = declarationRepository.save(declaration);
            return savedDeclaration.getIdPatient();
        } else throw new PatientEqualDoctorException();

    }

    @Override
    public void deleteDeclaration(long idPatient) {
        declarationRepository.deleteById(idPatient);
    }
}
