package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.repository.model.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    @Query(value = "{CALL GET_ALL_DOCTORS()}", nativeQuery = true)
    List<String> getAllDoctors();
    List<Declaration> findAllByIdDoctor(long idDoctor);
    Declaration findByIdPatient(long idPatient);

    @Query(value = "{CALL GET_ID_FROM_EMAIL(:email)}", nativeQuery = true)
    long getIdFromEmail(@Param("email") String email);

    @Query(value = "{CALL GET_ROLE_FROM_ID(:id)}", nativeQuery = true)
    long getRoleById(@Param("id") long id);
}
