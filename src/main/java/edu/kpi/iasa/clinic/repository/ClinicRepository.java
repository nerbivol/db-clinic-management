package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.repository.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

}
