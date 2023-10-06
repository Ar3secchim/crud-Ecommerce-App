package com.crud.repository;

import com.crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
  UserDetails findByEmail(String email);

//  @Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
//  Customer getCustomerByEmail(String email);

  @Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
  Customer findCustomerById(Integer id);

  @Query(value = "SELECT * FROM customers WHERE name LIKE '%s:name%' = :name", nativeQuery = true)
  List<Customer> findByName(String name);
}
