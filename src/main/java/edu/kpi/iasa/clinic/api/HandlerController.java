package edu.kpi.iasa.clinic.api;

import edu.kpi.iasa.clinic.exception.StatusNotFoundException;
import edu.kpi.iasa.clinic.exception.UserNotFoundException;
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
        Error error = Error.builder().code("BAD_REQUEST").description("Status Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Void> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
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
