package edu.kpi.iasa.clinic.repository;

import edu.kpi.iasa.clinic.repository.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByCode(String code);
    String getRoleById(long id);
}
