package com.crud.repository;

import com.crud.model.Customer;
import com.crud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> findByCustomer(Customer customer);
}
