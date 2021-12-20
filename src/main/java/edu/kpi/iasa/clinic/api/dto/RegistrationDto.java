package edu.kpi.iasa.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
