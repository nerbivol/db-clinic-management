package edu.kpi.iasa.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class VisitDto {
    long idPatient;
    String date;
    String time;
}

//public class VisitDto {
//    long idPatient;
//    int year;
//    int month;
//    int dayOfMonth;
//    int hour;
//    int minute;
//}
