package com.crud.controller;

import com.crud.controller.dto.customer.CustomerRequest;
import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;
import com.crud.service.CustomerService;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @PostMapping ("customer")
  public CustomerResponse createCustomer(@RequestBody CustomerRequest customerDTO){
    Customer customer = customerService.create(CustomerConvert.toEntity(customerDTO));
    return CustomerConvert.toResponse(customer);
  }

  @GetMapping("/customer")
  public List<CustomerResponse> getCustomer(){
    return customerService.listAll();
  }

  @GetMapping("/customer/document/{document}")
  public CustomerResponse getCustomerByDocument(@PathVariable String document){
    return  customerService.findByDocument(document);
  }

  @GetMapping("/customer/{id}")
  public CustomerResponse getCustomerById(@PathVariable String id){
    return customerService.findById(id);
  }

  @GetMapping("/customer/name/{name}")
  public List<CustomerResponse> getCustomerByName(@PathVariable String name){
    return customerService.findByName(name);
  }
}
