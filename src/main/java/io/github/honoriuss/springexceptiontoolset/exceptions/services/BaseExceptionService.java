package io.github.honoriuss.springexceptiontoolset.exceptions.services;

import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiErrorModel;
import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiUrlErrorModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class BaseExceptionService {
    @Value("${exception-toolset.api-error.include-url}")
    private boolean showUrl;

    public ResponseEntity<ApiErrorModel> createApiErrorMessage(String message, WebRequest webRequest, HttpServletRequest servletRequest) {
        var apiErrorModel = showUrl
                ? getApiErrorUrlModel(message, webRequest, servletRequest)
                : new ApiErrorModel(message);
        return new ResponseEntity<>(apiErrorModel, HttpStatus.BAD_REQUEST);
    }

    private ApiUrlErrorModel getApiErrorUrlModel(String message, WebRequest webRequest, HttpServletRequest servletRequest) {
        return new ApiUrlErrorModel(message, getUrl(webRequest, servletRequest));
    }

    private String getUrl(WebRequest webRequest, HttpServletRequest servletRequest) {
        if (webRequest != null) {
            return webRequest.getDescription(false);
        }
        return servletRequest.getRequestURI();
    }
}
