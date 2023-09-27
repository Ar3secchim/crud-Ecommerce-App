package com.crud.service;

import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;
import com.crud.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService{
  @Autowired
  CustomerRepository repository;

  public Customer create(Customer customer) {
    repository.save(customer);
    return customer;
  }

  public List<CustomerResponse> listAll() {
    List<Customer> listCustomer = repository.findAll();
    return CustomerConvert.toListResponse(listCustomer);
  }

  public CustomerResponse findByDocument(String document) {
    Customer found = null;

    if (document != null) {
      found = repository.findByDocument(document);
    }

    return CustomerConvert.toResponse(found);
  }

  public List<CustomerResponse> findByName(String name) {
    List<Customer> found = new ArrayList<>();

    if (name != null) {
      found = repository.findByName(name);
    }

    return CustomerConvert.toListResponse(found);
  }

  public CustomerResponse findById(String id) {
    Customer found = null;

    if (id != null) {
      found = repository.findById(id);
    }
    return CustomerConvert.toResponse(found);
  }
}
