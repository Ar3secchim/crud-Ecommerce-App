package com.crud.modules.customers.repository;

import com.crud.modules.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String>{
  @Query(value = "SELECT * FROM customers WHERE email = :email", nativeQuery = true)
  Customer findByEmail(String email);

  @Query(value = "SELECT * FROM customers WHERE sku = :id", nativeQuery = true)
  Customer findBySku(String id);

  @Query(value = "SELECT * FROM customers WHERE name LIKE '%s:name%' = :name", nativeQuery = true)
  List<Customer> findByName(String name);
}
