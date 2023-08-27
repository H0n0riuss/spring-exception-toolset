package io.github.honoriuss.springexceptiontoolset.exceptions.handler;

import io.github.honoriuss.springexceptiontoolset.exceptions.handler.abstracts.ABaseExceptionHandler;
import io.github.honoriuss.springexceptiontoolset.exceptions.services.BaseExceptionService;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseRestExceptionHandler extends ABaseExceptionHandler {

    public BaseRestExceptionHandler(BaseExceptionService baseExceptionService) {
        super(baseExceptionService);
    }
}
