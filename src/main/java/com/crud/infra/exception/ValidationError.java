package com.crud.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError extends Exception{
  private String field;
  private String message;
}
