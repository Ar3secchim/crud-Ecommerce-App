package com.crud.utils;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerConvert {
  public static Customer toEntity(CustomerRequest customerRequest){
    Customer customer = new Customer();

    customer.setSku(UUID.randomUUID().toString());
    customer.setName(customerRequest.getName());
    customer.setEmail(customerRequest.getEmail());
    customer.setAddress(customerRequest.getAddress());
    customer.setPassword(customerRequest.getPassword());
    return customer;
  }

  public static CustomerResponse toResponse(Customer customer){
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setSku(customer.getSku());
    customerResponse.setName(customer.getName());
    customerResponse.setEmail(customer.getEmail());
    return customerResponse;
  }

  public static List<CustomerResponse> toListResponse(List<Customer> listCustomer){
    if(listCustomer == null) return new ArrayList<>();

    return listCustomer.stream()
            .map(CustomerConvert::toResponse)
            .collect(Collectors.toList());
  }
}
