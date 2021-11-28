package edu.kpi.iasa.clinic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequestDto {
    String login;
    String password;
}
