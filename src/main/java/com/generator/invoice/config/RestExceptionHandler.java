package com.generator.invoice.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.generator.invoice.ExceptionHandling.ControllerResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     *
     * @param ex
     * All the run time exceptions that occur in the application will trigger the below method
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ControllerResponse> handleSystemException(Throwable ex) {
        return new ResponseEntity<ControllerResponse>(ControllerResponse.getFailureResponse(ex), HttpStatus.ACCEPTED);

    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ControllerResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

        return new ResponseEntity<ControllerResponse>(ControllerResponse.getFailureResponse(ex), HttpStatus.ACCEPTED);
    }
}
