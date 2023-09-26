package com.crud.service;

import com.crud.model.Customer;
import com.crud.repository.CustomerRepository;
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

  public List<Customer> listAll() {
    return repository.findAll();
  }

  public Customer findByDocument(String document) {
    Customer found = null;
    if (document != null) {
      found = repository.findByDocument(document);
    }
    return found;
  }

  public List<Customer> findByName(String name) {
    List<Customer> found = new ArrayList<>();

    if (name != null) {
      found = repository.findByName(name);
    }
    return found;
  }

  public Customer findById(String id) {
    Customer found = null;

    if (id != null) {
      found = repository.findById(id);
    }
    return found;
  }
}
