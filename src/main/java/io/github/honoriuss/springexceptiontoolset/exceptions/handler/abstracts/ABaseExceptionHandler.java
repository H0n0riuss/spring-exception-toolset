package io.github.honoriuss.springexceptiontoolset.exceptions.handler.abstracts;

import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiErrorModel;
import io.github.honoriuss.springexceptiontoolset.exceptions.services.BaseExceptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public abstract class ABaseExceptionHandler {
    @Value("${exception-toolset.api-error.http-status-code}")
    private int httpStatusCode;
    private final BaseExceptionService baseExceptionService;

    public ABaseExceptionHandler(BaseExceptionService baseExceptionService) {
        this.baseExceptionService = baseExceptionService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorModel> handleException(Exception ex,
                                                         WebRequest webRequest,
                                                         HttpServletRequest servletRequest) {
        return new ResponseEntity<>(
                baseExceptionService.createApiErrorMessage(ex.getMessage(), webRequest, servletRequest),
                HttpStatus.valueOf(httpStatusCode));
    }
}
