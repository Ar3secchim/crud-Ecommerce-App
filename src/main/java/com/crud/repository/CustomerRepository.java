package com.crud.repository;

import com.crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Customer findByDocument(String document);

  List<Customer> findByName(String name);
}
