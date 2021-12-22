package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.api.dto.AccountDto;
import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.repository.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    @Query(value = "{CALL GET_ALL_DOCTORS()}", nativeQuery = true)
    public List<String> getAllDoctors();

    List<Clinic> findAllByIdDoctor(long idDoctor);
}
