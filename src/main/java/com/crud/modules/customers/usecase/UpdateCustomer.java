package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UpdateCustomer {
  @Autowired
  CustomerRepository repository;

  public CustomerResponse execute(String id, CustomerRequest customerRequest) {
    Customer customer = repository.findCustomerById(id);

    customer.setName(customerRequest.getName());
    customer.setAddress(customer.getAddress());

    if(!Objects.equals(customerRequest.getEmail(), customer.getEmail())) {
      var email = repository.findByEmail(customerRequest.getEmail());

      if (email == null) {
        customer.setEmail(customerRequest.getEmail());
      }
    }

    customer.setEmail(customer.getEmail());
    customer.setSku(id);

    return CustomerConvert.toResponse(repository.save(customer));
  }
}
