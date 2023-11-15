package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCustomer {
  @Autowired
  CustomerRepository repository;

  public List<CustomerResponse> execute() {
    return CustomerConvert.toListResponse(repository.findAll());
  }
}
