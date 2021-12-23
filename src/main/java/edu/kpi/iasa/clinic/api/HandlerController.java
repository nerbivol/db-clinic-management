package edu.kpi.iasa.clinic.api;

import edu.kpi.iasa.clinic.exception.*;
import edu.kpi.iasa.clinic.repository.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HandlerController {
    private final MessageSource messageSource;

    public HandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value
            = { StatusNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            StatusNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("Bad Request").description("Status Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Void> handleUserNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler({EmailAddressAlreadyExistsException.class})
    public ResponseEntity<Error> handleConflict(EmailAddressAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("Bad Request").description("Email address already exists ").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({PhoneAlreadyExistsException.class})
    public ResponseEntity<Error> handleConflict(PhoneAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("Bad Request").description("Phone already exists ").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({PatientEqualDoctorException.class})
    public ResponseEntity<Error> handleConflict(PatientEqualDoctorException ex, WebRequest request) {
        Error error = Error.builder().code("Bad Request").description("The doctor cannot treat himself").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({PatientNotDoctorException.class})
    public ResponseEntity<Error> handleConflict(PatientNotDoctorException ex, WebRequest request) {
        Error error = Error.builder().code("Bad Request").description("The declaration cannot be signed with the patient").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<Error>> validationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        log.info("ex:", ex.getConstraintViolations().toArray());
        List<Error> errors = ex.getConstraintViolations().stream().map(violation ->
                Error.builder().description(violation.getPropertyPath() + " invalid. " +
                                messageSource.getMessage(violation.getMessage(), null, request.getLocale()))
                        .code("Bad Request").build()
        ).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }
}
