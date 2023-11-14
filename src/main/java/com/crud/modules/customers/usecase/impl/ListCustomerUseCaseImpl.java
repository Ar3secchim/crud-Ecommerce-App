package com.crud.modules.customers.usecase.impl;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.IListCustomerUseCase;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCustomerUseCaseImpl implements IListCustomerUseCase {
  @Autowired
  CustomerRepository repository;

  @Override
  public List<CustomerResponse> listAll() {
    return CustomerConvert.toListResponse(repository.findAll());
  }
}
