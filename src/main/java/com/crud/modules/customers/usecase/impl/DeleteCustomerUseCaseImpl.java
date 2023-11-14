package com.crud.modules.customers.usecase.impl;

import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.IDeleteCustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerUseCaseImpl implements IDeleteCustomerUseCase {
  @Autowired
  CustomerRepository repository;

  public void delete(String id){
    if (id != null) {
      repository.deleteById(id);
    }
  }
}
