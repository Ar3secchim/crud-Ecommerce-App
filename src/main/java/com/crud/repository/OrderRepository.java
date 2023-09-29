package com.crud.repository;

import com.crud.model.Customer;
import com.crud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{
  Order findByCustomer(Customer customer);
}
