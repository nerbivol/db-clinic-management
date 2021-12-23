package edu.kpi.iasa.clinic.service.impl;

import edu.kpi.iasa.clinic.exception.EmailAddressAlreadyExistsException;
import edu.kpi.iasa.clinic.exception.PhoneAlreadyExistsException;
import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.repository.UserRepository;
import edu.kpi.iasa.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public Account createAccount(Account account) {
        return userRepository.save(account);
    }

    @Override
    public void checkUserRegisterByEmail(String email) {
       final Optional<Account> maybeUser = userRepository.findByEmail(email);
       if(maybeUser.isPresent()) throw new EmailAddressAlreadyExistsException();
    }

    @Override
    public void checkUserRegisterByPhone(String phone) {
        final Optional<Account> maybeUser = userRepository.findByPhone(phone);
        if(maybeUser.isPresent()) throw new PhoneAlreadyExistsException();
    }
}
