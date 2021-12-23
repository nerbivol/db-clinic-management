package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.repository.model.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    @Query(value = "{CALL GET_ALL_DOCTORS()}", nativeQuery = true)
    public List<String> getAllDoctors();
    List<Declaration> findAllByIdDoctor(long idDoctor);
    Declaration findByIdPatient(long idPatient);
}
