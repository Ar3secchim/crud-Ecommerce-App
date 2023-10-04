package com.crud.controller;

import com.crud.controller.dto.customer.CustomerRequest;
import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.QCustomer;
import com.crud.repository.CustomerRepository;
import com.crud.service.CustomerService;
import com.crud.utils.CustomerConvert;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerDTO){
    CustomerResponse customerResponse = customerService.create(CustomerConvert.toEntity(customerDTO));
    return ResponseEntity.created(
            URI.create("/customer/"+customerResponse.getId()))
            .body(customerResponse);
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
    return ResponseEntity.ok(customerService.listAll());
  }

  @GetMapping("document/{email}")
  public ResponseEntity<CustomerResponse> getCustomerByEmail( @PathVariable String email){
    return ResponseEntity.ok(customerService.findByEmail(email));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id){
    return ResponseEntity.ok(customerService.findById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<CustomerResponse>> getCustomerByName(@PathVariable String name){
    return ResponseEntity.ok(customerService.findByName(name));
  }

  //TODO delete customer
  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponse> deleteCustomer(@PathVariable Integer id){
    customerService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
