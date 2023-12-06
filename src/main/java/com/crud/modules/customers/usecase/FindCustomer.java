package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCustomer {
  private final CustomerRepository repository;

  public CustomerResponse findByEmail(String email) {
    var customer = repository.findByEmail(email);

    if(customer == null){
      throw new EntityNotFoundException("Customer not found with email: " + email);
    }

    return CustomerConvert.toResponse(customer);
  }

  public CustomerResponse findById(String id) {
    var customer = repository.findBySku(id);

    if(customer == null){
      throw new EntityNotFoundException("Customer not found with ID: " + id);
    }

    return CustomerConvert.toResponse(customer);
  }

  public List<CustomerResponse> findByName(String name) {

    if (name == null) {
      throw new EntityNotFoundException("Name not null");
    }

    List<Customer> found = repository.findByName(name);

    return CustomerConvert.toListResponse(found);
  }
}
