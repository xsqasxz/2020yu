package com.small.config;

import com.small.entity.JsonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LogManager.getLogger();

    @ExceptionHandler(value = BindException.class)
    public Object validationExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.debug("前端校验不通过：{}",message);
        return JsonResponse.error(message);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object validationConstraintViolationException(ConstraintViolationException e) {
        log.debug("前端校验不通过：{}",e.getMessage());
        return JsonResponse.error(e.getConstraintViolations().stream().map(ConstraintViolation:: getMessage).collect(Collectors.joining()));
    }

}
