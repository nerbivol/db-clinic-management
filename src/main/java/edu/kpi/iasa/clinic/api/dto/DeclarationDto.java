package edu.kpi.iasa.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class DeclarationDto {
    long idDoctor;
    long idPatient;
}
