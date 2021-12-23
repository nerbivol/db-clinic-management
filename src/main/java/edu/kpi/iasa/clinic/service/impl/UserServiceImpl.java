package edu.kpi.iasa.clinic.service.impl;

import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.repository.UserRepository;
import edu.kpi.iasa.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public Account createAccount(Account account) {
        return userRepository.save(account);
    }
}
