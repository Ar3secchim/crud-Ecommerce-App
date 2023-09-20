package com.crud.ecommerce.app.application.repository;

import com.crud.ecommerce.app.domain.Customer;
import com.crud.ecommerce.app.domain.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
  List<Order> findByCustomer(Customer customer);
}
