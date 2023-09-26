package com.crud.controller;

import com.crud.controller.dto.customer.CustomerRequest;
import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;
import com.crud.service.CustomerService;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<CustomerResponse>> getCustomer(){
    List<Customer> listCustomer = customerService.listAll();
    return ResponseEntity.ok(CustomerConvert.toListResponse(listCustomer));
  }

  @GetMapping("/customer/document/{document}")
  public CustomerResponse getCustomerByDocument(@PathVariable String document){
    Customer customer = customerService.findByDocument(document);
    return CustomerConvert.toResponse(customer);
  }

  @GetMapping("/customer/{id}")
  public CustomerResponse getCustomerById(@PathVariable String id){
    Customer customer = customerService.findById(id);
    return CustomerConvert.toResponse(customer);
  }

  @GetMapping("/customer/name/{name}")
  public List<CustomerResponse> getCustomerByName(@PathVariable String name){
    List<Customer> listCustomer =  customerService.findByName(name);
    return CustomerConvert.toListResponse(listCustomer);
  }
}
