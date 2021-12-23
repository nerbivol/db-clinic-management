package edu.kpi.iasa.clinic.service.impl;

import edu.kpi.iasa.clinic.repository.ClinicRepository;
import edu.kpi.iasa.clinic.repository.model.Clinic;
import edu.kpi.iasa.clinic.repository.model.Visit;
import edu.kpi.iasa.clinic.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    @Override
    public List<Clinic> getAllDiagnostic() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic getDiagnosticById(long id) {
        final Optional<Clinic> maybeClinic = clinicRepository.findById(id);
        if(maybeClinic.isEmpty()) throw new IllegalArgumentException("Invalid Id");

        return maybeClinic.get();
    }

    @Override
    public long createDiagnostic(Clinic clinic) {
        final Clinic savedClinic = clinicRepository.save(clinic);
        return savedClinic.getId();
    }

    @Override
    public void updateDiagnostic(long id, long idPatient, String complains, String conclusion,
                                 String diagnose, double price, String additionalReview) {
        final Optional<Clinic> maybeClinic = clinicRepository.findById(id);
        if(maybeClinic.isEmpty()) throw new IllegalArgumentException("Invalid Id");

        final Clinic clinic = maybeClinic.get();
        clinic.setIdPatient(idPatient);
        if(complains!=null && !complains.isBlank()) clinic.setComplains(complains);
        if(conclusion!=null && !conclusion.isBlank()) clinic.setConclusion(conclusion);
        if(diagnose!=null && !diagnose.isBlank()) clinic.setDiagnose(diagnose);
        clinic.setPrice(price);
        if(additionalReview != null && !additionalReview.isBlank()) clinic.setAdditionalReview(additionalReview);
        clinic.setDateUpdated(LocalDate.now());
    }

    @Override
    public void deleteDiagnostic(long id) {
        clinicRepository.deleteById(id);
    }
}
