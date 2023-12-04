package com.crud.modules.customers.usecase;

import com.crud.infra.exception.PasswordValidationError;
import com.crud.infra.exception.ValidationError;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import com.crud.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomer {
  private final CustomerRepository repository;
  private final PasswordEncoder passwordEncoder;

  public CustomerResponse execute(Customer customer) throws PasswordValidationError, ValidationError {
    if(!Validator.name(customer.getName())) throw new ValidationError(customer.getName(), "Nome menor que dois " +
            "character");

    if(!Validator.passwordValidate(customer.getPassword())) throw new PasswordValidationError("Senha deve seguir o " +
            "padrão");

    if(!Validator.emailValidate(customer.getEmail())) throw new ValidationError(customer.getEmail(), "Email inválido");

    String encodePassword = passwordEncoder.encode(customer.getPassword());
    customer.setPassword(encodePassword);

    repository.save(customer);

    return CustomerConvert.toResponse(customer);
  }
}
