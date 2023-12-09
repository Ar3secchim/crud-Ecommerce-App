package com.crud.infra.configuration;

import com.crud.infra.exception.BadRequestClient;
import com.crud.infra.exception.PasswordValidationError;
import com.crud.infra.exception.ValidationErrorObject;
import com.crud.infra.exception.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
  @Autowired
  private MessageSource messageSource;

  private final Logger log = LoggerFactory.getLogger(getClass());

//  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  @ResponseBody
//  public List<ValidationErrorObject> handler(MethodArgumentNotValidException exception){
//    List<ValidationErrorObject> errors = new ArrayList<>();
//    List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
//
//    fieldErros.forEach( e -> {
//      String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
//      ValidationError validationError =  new ValidationError(e.getField(), message);
//      ValidationErrorObject validateErrorObject = new ValidationErrorObject(
//              validationError.getField(), validationError.getMessage());
//
//      errors.add(validateErrorObject);
//    });
//
//    return errors;
//  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleMethodArgumentNotValidException(
          MethodArgumentNotValidException ex,
          WebRequest request
  ) {
    log.warn("Unhandled exception:", ex);
    var errors = new ArrayList<Map<String, String>>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(Map.of(error.getField(), error.getDefaultMessage()));
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(Map.of(error.getObjectName(), error.getDefaultMessage()));
    }

    var body = Map.of(
            "code", HttpStatus.BAD_REQUEST,
            "errors", errors
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(body);
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(PasswordValidationError.class)
  public List<String> handlerPassword(PasswordValidationError exception){
    return Collections.singletonList(exception.getMessage());
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestClient.class)
  public List<String> BadRequest(BadRequestClient exception){
    return Collections.singletonList(exception.getMessage());
  }
}
