package com.crud.modules.customers.usecase.impl;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.IFindCustomerUseCase;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindCustomerUseCaseImpl implements IFindCustomerUseCase {
  @Autowired
  CustomerRepository repository;

  @Override
  public CustomerResponse findByEmail(String email) {
    Customer customer = repository.findByEmail(email);
    return CustomerConvert.toResponse(customer);
  }

  @Override
  public CustomerResponse findById(String id) {
    Customer customer = repository.findCustomerById(id);
    return CustomerConvert.toResponse(customer);
  }

  public List<CustomerResponse> findByName(String name) {
    List<Customer> found = new ArrayList<>();

    if (name != null) {
      found = repository.findByName(name);
    }

    return CustomerConvert.toListResponse(found);
  }

}
