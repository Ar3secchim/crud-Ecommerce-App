package com.crud.utils;

import com.crud.controller.dto.customer.CustomerRequest;
import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerConvert {
  public static Customer toEntity(CustomerRequest customerRequest){
    Customer customer = new Customer();
    customer.setName(customerRequest.getName());
    customer.setEmail(customerRequest.getEmail());
    customer.setAddress(customerRequest.getAddress());
    customer.setPassword(customerRequest.getPassword());
    return customer;
  }

  public static CustomerResponse toResponse(Customer customer){
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setId(customer.getId());
    customerResponse.setName(customer.getName());
    customerResponse.setEmail(customer.getEmail());
    return customerResponse;
  }

  public static List<CustomerResponse> toListResponse(List<Customer> listCustomer){
    return listCustomer.stream()
            .map(CustomerConvert::toResponse)
            .collect(Collectors.toList());
  }
}
