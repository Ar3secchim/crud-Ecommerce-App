package com.crud.controller;

import com.crud.model.Customer;
import com.crud.usecases.impl.CustomerUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
  @Autowired
  CustomerUseCaseImpl customerService;

  @GetMapping("/customer")
  public List<Customer> getCustomer(){
    return customerService.list();
  }

  @GetMapping("/customer/{document}")
  public Customer getCustomerByDocument(@PathVariable String document){
    return customerService.findByDocument(document);
  }

  @GetMapping("/customer/name/{name}")
  public List<Customer> getCustomerByName(@PathVariable String name){
    return customerService.findByName(name);
  }

  @PostMapping ("customer")
  public Customer createCustomer(@RequestBody Customer customer){
    customerService.create(customer);
    return customer;
  }
}
