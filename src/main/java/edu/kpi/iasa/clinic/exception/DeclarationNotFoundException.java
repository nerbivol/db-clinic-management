package edu.kpi.iasa.clinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Declaration does not exist")
public class DeclarationNotFoundException extends RuntimeException{
}
