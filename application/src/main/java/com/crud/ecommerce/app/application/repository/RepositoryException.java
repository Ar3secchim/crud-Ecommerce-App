package com.crud.ecommerce.app.application.repository;

public class RepositoryException extends RuntimeException {

  public RepositoryException(String message, Exception cause) {
    super(message, cause);
  }

}
