package com.crud.modules.order.repository;

import com.crud.modules.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, String>{
  @Query(value = "SELECT * FROM orders WHERE sku = :id", nativeQuery = true)
  Order findOrderById(String id);
}
