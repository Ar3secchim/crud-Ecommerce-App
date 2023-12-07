package com.crud.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorObject {
  String field;
  String message;
}
