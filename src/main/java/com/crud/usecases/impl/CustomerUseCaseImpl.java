package com.crud.usecases.impl;

import com.crud.model.Customer;
import com.crud.repository.CustomerRepository;
import com.crud.usecases.ICustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUseCaseImpl implements ICustomerUseCase {
  @Autowired
  CustomerRepository repository;

  @Override
  public Customer create(Customer customer) {
    repository.save(customer);
    return customer;
  }

  @Override
  public List<Customer> list() {
    return repository.findAll();
  }

  @Override
  public Customer findByDocument(String document) {
    Customer found = null;
    if (document != null) {
      found = repository.findByDocument(document);
    }
    return found;
  }

  @Override
  public List<Customer> findByName(String name) {
    List<Customer> found = new ArrayList<>();

    if (name != null) {
      found = repository.findByName(name);
    }
    return found;
  }

  @Override
  public void update(Customer customer) {

  }

  @Override
  public Customer delete(Long id) {
    return null;
  }
}
