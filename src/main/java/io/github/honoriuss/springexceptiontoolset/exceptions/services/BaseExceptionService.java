package io.github.honoriuss.springexceptiontoolset.exceptions.services;

import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiErrorModel;
import io.github.honoriuss.springexceptiontoolset.exceptions.models.ApiUrlErrorModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class BaseExceptionService {
    @Value("${exception-toolset.api-error.include-url}")
    private boolean showUrl;
    @Value("${exception-toolset.api-error.include-client-info}")
    private boolean includeClientInfo;

    public ApiErrorModel createApiErrorMessage(String message, WebRequest webRequest) {
        return showUrl
                ? getApiErrorUrlModel(message, webRequest)
                : new ApiErrorModel(message);
    }

    private ApiUrlErrorModel getApiErrorUrlModel(String message, WebRequest webRequest) {
        return new ApiUrlErrorModel(message, getUrl(webRequest));
    }

    private String getUrl(WebRequest webRequest) {
        return webRequest.getDescription(includeClientInfo);
    }
}
