package io.github.nayetdet.catalog.exception.handler;

import io.github.nayetdet.catalog.exception.AbstractException;
import io.github.nayetdet.catalog.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    public final ResponseEntity<ExceptionResponse> handleAbstractExceptions(AbstractException ex) {
        var responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        var httpStatus = responseStatus != null
                ? responseStatus.value()
                : HttpStatus.INTERNAL_SERVER_ERROR;

        var response = new ExceptionResponse(httpStatus.value(), ex.getMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

}
