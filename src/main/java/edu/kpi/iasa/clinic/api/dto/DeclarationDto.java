package edu.kpi.iasa.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeclarationDto {
    long idPatient;
    long idDoctor;
}
