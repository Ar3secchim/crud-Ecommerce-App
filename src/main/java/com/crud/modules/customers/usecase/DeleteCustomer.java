package com.crud.modules.customers.usecase;

import com.crud.modules.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomer {
  private final CustomerRepository repository;

  public void execute(String id) throws Exception {
    var customer = repository.findById(id);

    if (customer.isPresent()) {
      repository.delete(customer.get());
    }else {
       throw new EntityNotFoundException("Customer not found with ID: " + id);
    }
  }
}
