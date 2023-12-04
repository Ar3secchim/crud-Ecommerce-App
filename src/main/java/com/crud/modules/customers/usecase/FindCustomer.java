package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCustomer {
  private final CustomerRepository repository;

  public CustomerResponse findByEmail(String email) {

    Customer customer = repository.findByEmail(email);
    return CustomerConvert.toResponse(customer);
  }

  public CustomerResponse findById(String id) {
    Customer customer = repository.findById(id).get();
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
