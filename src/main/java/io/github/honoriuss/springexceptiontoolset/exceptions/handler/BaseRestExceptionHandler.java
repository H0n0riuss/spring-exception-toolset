package io.github.honoriuss.springexceptiontoolset.exceptions.handler;

import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiErrorModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class BaseRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorModel> handleException(Exception ex,
                                                         WebRequest webRequest,
                                                         HttpServletRequest servletRequest) {
        ApiErrorModel apiErrorModel = new ApiErrorModel(ex.getMessage());
        return new ResponseEntity<>(apiErrorModel, HttpStatus.BAD_REQUEST);
    }
}
