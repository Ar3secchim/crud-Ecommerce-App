package com.crud.modules.customers.usecase;

import com.crud.modules.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomer {
  @Autowired
  CustomerRepository repository;

  public void execute(String id){
    var customer = repository.findById(id);

    if (customer != null) {
      repository.delete(customer.get());
    }
  }
}
