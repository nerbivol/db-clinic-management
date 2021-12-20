package edu.kpi.iasa.clinic.service.impl;

import edu.kpi.iasa.clinic.configuration.security.UserPrincipal;
import edu.kpi.iasa.clinic.exception.UserNotFoundException;
import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        Account user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new UserPrincipal(user);
    }
}
