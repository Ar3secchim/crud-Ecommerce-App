package com.crud.modules.customers.usecase.impl;

import com.crud.infra.exception.PasswordValidationError;
import com.crud.infra.exception.ValidationError;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.ICreateCustomerUseCase;
import com.crud.utils.CustomerConvert;
import com.crud.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerUsecase implements ICreateCustomerUseCase {
  @Autowired
  CustomerRepository repository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public CustomerResponse create(Customer customer) throws PasswordValidationError, ValidationError {
    String encodePassword = passwordEncoder.encode(customer.getPassword());
    customer.setPassword(encodePassword);

    if(!Validator.name(customer.getEmail())) throw new ValidationError(customer.getName(), "Nome menor que dois " +
            "character");
    if(!Validator.emailValidate(customer.getEmail())) throw new ValidationError(customer.getEmail(), "Email inválido");
    if(!Validator.passwordValidate(customer.getPassword())) throw new PasswordValidationError("Senha deve seguir o " +
            "padrão");
    return CustomerConvert.toResponse(repository.save(customer));
  }
}
