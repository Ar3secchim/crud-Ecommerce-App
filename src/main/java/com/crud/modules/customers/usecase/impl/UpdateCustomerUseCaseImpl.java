package com.crud.modules.customers.usecase.impl;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.IUpdateCustomerUseCase;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerUseCaseImpl implements IUpdateCustomerUseCase {
  @Autowired
  CustomerRepository repository;

  @Override
  public CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest) {
    Customer customer = CustomerConvert.toEntity(customerRequest);
    customer.setId(id);
    return CustomerConvert.toResponse(repository.save(customer));
  }
}
