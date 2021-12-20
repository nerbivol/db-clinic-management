package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.repository.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
//    List<Clinic> findAllByDoctor(long idDoctor);
//    Clinic findByPatient(long idPatient);
}
