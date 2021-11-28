package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.exception.RoleNotFoundException;
import edu.kpi.iasa.clinic.model.Role;
import edu.kpi.iasa.clinic.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findRoleByCode(code).orElseThrow(RoleNotFoundException::new);
    }
}
