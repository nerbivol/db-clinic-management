package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Account createAccount(Account account) {
        return userRepository.save(account);
    }
}
