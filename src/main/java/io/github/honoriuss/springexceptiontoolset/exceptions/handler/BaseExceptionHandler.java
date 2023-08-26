package io.github.honoriuss.springexceptiontoolset.exceptions.handler;

import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiErrorModel;
import io.github.honoriuss.springexceptiontoolset.exceptions.services.BaseExceptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BaseExceptionHandler {
    private final BaseExceptionService baseExceptionService;

    public BaseExceptionHandler(BaseExceptionService baseExceptionService) {
        this.baseExceptionService = baseExceptionService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorModel> handleException(Exception ex,
                                                         WebRequest webRequest,
                                                         HttpServletRequest servletRequest) {
        return new ResponseEntity<>(
                baseExceptionService.createApiErrorMessage(ex.getMessage(), webRequest, servletRequest),
                HttpStatus.BAD_REQUEST);
    }
}
