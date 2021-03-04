package io.orange.mercadolivre.config.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ErrorValidationHandler {

    @Autowired
    private MessageSource messageSource;

    private HttpRequestMethodNotSupportedException exception;


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorFormDto> handle(MethodArgumentNotValidException exception) {
        List<ErrorFormDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            Object rejected = e.getRejectedValue();

            ErrorFormDto error = new ErrorFormDto(e.getField(), message, rejected);
            dto.add(error);

        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public List<ErrorFormDto> username(HttpRequestMethodNotSupportedException exception){

        List<ErrorFormDto> dto = new ArrayList<>();
        String message = exception.getMessage();
        String method = exception.getMethod();
        Object rejected = null;

        ErrorFormDto error = new ErrorFormDto(method,message, null);
        dto.add(error);

        return dto;
    }
}
