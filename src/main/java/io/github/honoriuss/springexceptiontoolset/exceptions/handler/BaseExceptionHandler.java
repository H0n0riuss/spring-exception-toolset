package io.github.honoriuss.springexceptiontoolset.exceptions.handler;

import io.github.honoriuss.springexceptiontoolset.exceptions.handler.abstracts.ABaseExceptionHandler;
import io.github.honoriuss.springexceptiontoolset.exceptions.services.BaseExceptionService;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BaseExceptionHandler extends ABaseExceptionHandler {

    public BaseExceptionHandler(BaseExceptionService baseExceptionService) {
        super(baseExceptionService);
    }
}
