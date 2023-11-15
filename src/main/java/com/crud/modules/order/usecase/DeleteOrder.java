package com.crud.modules.order.usecase;

import com.crud.modules.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrder{
  @Autowired
  OrderRepository orderRepository;

  public void execute(String id) {
   var order = orderRepository.findOrderById(id);

    if (id != null) {
      orderRepository.delete(order);
    }
  }
}