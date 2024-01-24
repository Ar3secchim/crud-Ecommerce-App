package com.crud.modules.customers.usecase;

import com.crud.infra.exception.BadRequestClient;
import com.crud.infra.exception.PasswordValidationError;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import com.crud.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomer {
  private final CustomerRepository repository;
  private final PasswordEncoder passwordEncoder;

  public CustomerResponse execute(Customer customer) throws Exception {
    if(!Validator.name(customer.getName()))
      throw new Exception("length must be between 3 and 35");
    if(!Validator.passwordValidate(customer.getPassword()))
      throw new PasswordValidationError("Senha deve seguir o padrão");

    checkEmailAvailability(customer.getEmail());
    passwordEncoder(customer);

    repository.save(customer);
    return CustomerConvert.toResponse(customer);
  }

  private void checkEmailAvailability(String email) throws Exception {
    if(!Validator.emailValidate(email))
      throw new Exception("must be a well-formed email address");

    Customer emailExist = repository.findByEmail(email);
    if  (emailExist != null ) {
      throw new BadRequestClient("Email já está em uso");
    }
  }

  private void passwordEncoder(Customer customer){
    var passwordCryptographic = passwordEncoder.encode(customer.getPassword());
    customer.setPassword(passwordCryptographic);
  }
}
