package br.com.carv.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.carv.exception.ResourceNotFound;
import br.com.carv.exception.response.ExceptionResponse;

@ControllerAdvice
public class AutomatedTestsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFound exception) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("Bad Request", HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
