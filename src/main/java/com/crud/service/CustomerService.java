package com.crud.service;

import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.controller.exception.PasswordValidationError;
import com.crud.controller.exception.ValidationError;
import com.crud.model.Customer;
import com.crud.usecases.ICustomerUseCase;
import com.crud.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import com.crud.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerUseCase {
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
    if(!Validator.passwordValidate(customer.getPassword())) throw new PasswordValidationError("Senha deve seguir o padrao");
    return CustomerConvert.toResponse(repository.save(customer));
  }

  public List<CustomerResponse> listAll() {
    return CustomerConvert.toListResponse(repository.findAll());
  }

  public CustomerResponse findByEmail(String email) {
    Customer customer = (Customer) repository.findByEmail(email);
    return CustomerConvert.toResponse(customer);
  }

  public CustomerResponse findById(Integer id) {
    Customer found = null;

    if (id != null) {
      found = repository.findCustomerById(id);
    }

    return CustomerConvert.toResponse(found);
  }

  public List<CustomerResponse> findByName(String name) {
    List<Customer> found = new ArrayList<>();

    if (name != null) {
      found = repository.findByName(name);
    }

    return CustomerConvert.toListResponse(found);
  }

  public void delete(Integer id){
    if (id != null) {
      repository.deleteById(id);
    }
  }
}
