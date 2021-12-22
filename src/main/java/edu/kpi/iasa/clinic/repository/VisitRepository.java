package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.repository.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
