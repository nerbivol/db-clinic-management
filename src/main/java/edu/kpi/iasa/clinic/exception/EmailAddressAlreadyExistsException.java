package edu.kpi.iasa.clinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "email address already exists")
public class EmailAddressAlreadyExistsException extends RuntimeException{
}
