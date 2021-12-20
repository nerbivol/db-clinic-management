package edu.kpi.iasa.clinic.repository.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Error {
    String code;
    String description;
}
