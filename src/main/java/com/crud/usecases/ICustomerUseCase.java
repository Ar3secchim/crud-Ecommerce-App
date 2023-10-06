package com.crud.usecases;

import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.controller.exception.PasswordValidationError;
import com.crud.controller.exception.ValidationError;
import com.crud.model.Customer;

import java.util.List;

public interface ICustomerUseCase {
  CustomerResponse create(Customer customer) throws PasswordValidationError, ValidationError;
  List<CustomerResponse> listAll();
  CustomerResponse findByEmail(String email);
  CustomerResponse findById(Integer id);
  List<CustomerResponse> findByName(String name);
}
