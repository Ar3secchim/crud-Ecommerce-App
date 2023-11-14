package com.crud.modules.orderItem.repository;

import com.crud.modules.order.entity.Order;
import com.crud.modules.orderItem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdemItemRepository extends JpaRepository<OrderItem, String> {
  @Query(value = "SELECT * FROM order_item WHERE sku = :id", nativeQuery = true)
  OrderItem findOrderItemById(String id);
}
