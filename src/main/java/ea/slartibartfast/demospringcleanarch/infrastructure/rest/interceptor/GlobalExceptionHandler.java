package ea.slartibartfast.demospringcleanarch.infrastructure.interceptor;


import ea.slartibartfast.demospringcleanarch.domain.model.exception.DomainException;
import ea.slartibartfast.demospringcleanarch.domain.model.exception.ValidationException;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    @ExceptionHandler(value = {AuthenticationException.class})
    ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(value = {ValidationException.class})
    ResponseEntity<ApiResponse> handleEmailAlreadyUsedException(ValidationException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DomainException.class})
    ResponseEntity<ApiResponse> handleDomainException(DomainException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}