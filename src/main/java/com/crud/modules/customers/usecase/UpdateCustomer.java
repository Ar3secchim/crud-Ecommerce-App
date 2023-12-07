package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomer {
  @Autowired
  CustomerRepository repository;

  public CustomerResponse execute(String id, CustomerRequest customerRequest) throws Exception {
    Customer customer = repository.findByIdTransaction(id);

    if(customer == null) throw new Exception("Customer not found");

    customer.setName(customerRequest.getName());
    customer.setAddress(customerRequest.getAddress());
    customer.setIdTransaction(id);
    repository.save(customer);

    return CustomerConvert.toResponse(customer);
  }
}
