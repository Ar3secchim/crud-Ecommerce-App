package com.crud.modules.customers.usecase;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomer {
  private final CustomerRepository repository;

  public void execute(String id) throws Exception {
    Customer customer = repository.findByIdTransaction(id);
    if (customer == null) throw new BadRequestClient("Customer not found with ID: " + id);
    repository.delete(customer);
  }
}
