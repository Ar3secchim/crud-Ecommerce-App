package com.crud.modules.customers.usecase;

import com.crud.infra.exception.PasswordValidationError;
import com.crud.infra.exception.ValidationError;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;

public interface ICreateCustomerUseCase {
  CustomerResponse create(Customer customer) throws PasswordValidationError, ValidationError;
}
