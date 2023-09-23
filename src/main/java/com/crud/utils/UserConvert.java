package com.crud.utils;

import com.crud.controller.dto.customer.CustomerRequest;
import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class UserConvert {
  public static Customer toEntity(CustomerRequest customerRequest){
    Customer customer = new Customer();
    customer.setName(customerRequest.getName());
    customer.setDocument(customerRequest.getDocument());
    customer.setEmail(customerRequest.getEmail());
    customer.setTelephone(customerRequest.getTelephone());
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
    return listCustomer.stream().map(UserConvert::toResponse).collect(Collectors.toList());
  }
}
