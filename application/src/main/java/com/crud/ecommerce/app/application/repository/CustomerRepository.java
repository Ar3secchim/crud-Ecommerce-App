package com.crud.ecommerce.app.application.repository;

import com.crud.ecommerce.app.domain.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
  Customer findByDocument(String document);

  List<Customer> findByName(String name);
}
