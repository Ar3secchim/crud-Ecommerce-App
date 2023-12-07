package com.crud.modules.customers.usecase;

import com.crud.modules.customers.entity.Customer;
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
    Customer customer = repository.findBySku(id);
    if (customer == null) throw new EntityNotFoundException("Customer not found with ID: " + id);
    repository.delete(customer);
  }
}
