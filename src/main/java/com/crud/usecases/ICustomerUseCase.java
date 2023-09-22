package com.crud.usecases;

import com.crud.model.Customer;

import java.util.List;

public interface ICustomerUseCase{
  Customer create(Customer customer);

  List<Customer> list();

  Customer findByDocument(String document);

  List<Customer> findByName(String name);

  void update(Customer customer);

  Customer delete(Long id);
}
