package edu.kpi.iasa.clinic.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequestDto {
    String email;
    String password;
}
