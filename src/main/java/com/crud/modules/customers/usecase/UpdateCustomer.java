package com.crud.modules.customers.usecase;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DTO.CustomerRequestUpdate;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import com.crud.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomer {
  @Autowired
  CustomerRepository repository;

  public CustomerResponse execute(String id, CustomerRequestUpdate customerRequest) throws Exception {
    Customer customer = repository.findByIdTransaction(id);

    if(customer == null) throw new BadRequestClient("Customer not found");

    if(!Validator.name(customerRequest.getName()))
      throw new Exception("length must be between 3 and 35");

    customer.setName(customerRequest.getName());
    customer.setAddress(customerRequest.getAddress());
    customer.setIdTransaction(id);
    repository.save(customer);

    return CustomerConvert.toResponse(customer);
  }
}
