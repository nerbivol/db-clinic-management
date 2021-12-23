package edu.kpi.iasa.clinic.api;


import edu.kpi.iasa.clinic.configuration.security.jwt.JwtProcessor;
import edu.kpi.iasa.clinic.api.dto.AccountDto;
import edu.kpi.iasa.clinic.api.dto.JwtRequestDto;
import edu.kpi.iasa.clinic.api.dto.JwtResponseDto;
import edu.kpi.iasa.clinic.api.dto.RegistrationDto;
import edu.kpi.iasa.clinic.repository.model.Account;
import edu.kpi.iasa.clinic.service.RoleService;
import edu.kpi.iasa.clinic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Controller
public class AuthenticationController {

    private static final String DEFAULT_ROLE = "USER";

    private final AuthenticationManager authenticationManager;
    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;
    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtProcessor jwtProcessor,
                                    UserDetailsService userDetailsService,
                                    UserServiceImpl userServiceImpl,
                                    PasswordEncoder passwordEncoder,
                                    RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtProcessor = jwtProcessor;
        this.userDetailsService = userDetailsService;
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto jwtRequestDto) {
        String email = jwtRequestDto.getEmail();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, jwtRequestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtProcessor.createJwt(email,
                (Collection<GrantedAuthority>) userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signUp(@RequestBody RegistrationDto registrationDto) {
        userServiceImpl.checkUserRegisterByEmail(registrationDto.getEmail());
        userServiceImpl.checkUserRegisterByPhone(registrationDto.getPhone());
        Account account = createAccount(registrationDto);
        return ResponseEntity.ok(createAccountDto(userServiceImpl.createAccount(account)));
    }

    private AccountDto createAccountDto(Account account) {
        return new AccountDto(account.getEmail());
    }

    private Account createAccount(RegistrationDto registrationDto) {
        Account account = Account.builder()
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .phone(registrationDto.getPhone())
                .dateCreated(LocalDate.now())
                .enabled(Boolean.TRUE)
                .build();
        account.setRoles(Collections.singleton(roleService.getRoleByCode(DEFAULT_ROLE)));
        return account;
    }
}