package io.github.honoriuss.springexceptiontoolset.exceptions.services;

import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiErrorModel;
import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiUrlErrorModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class BaseExceptionService {
    @Value("${exception-toolset.api-error.include-url:true}")
    private boolean showUrl;

    public ApiErrorModel createApiErrorMessage(String message, WebRequest webRequest, HttpServletRequest servletRequest) {
        return showUrl
                ? getApiErrorUrlModel(message, webRequest, servletRequest)
                : new ApiErrorModel(message);
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
