package edu.kpi.iasa.clinic.service;

import edu.kpi.iasa.clinic.repository.model.Account;

public interface UserService {
    Account createAccount(Account account);
    void checkUserRegisterByEmail(String email);
    void checkUserRegisterByPhone(String phone);
}
