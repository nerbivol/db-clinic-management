package edu.kpi.iasa.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class AccountDto {
    long id;
    String FirstName;
    String LastName;
}