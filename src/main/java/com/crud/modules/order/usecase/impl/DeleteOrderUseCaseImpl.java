package com.crud.modules.order.usecase.impl;

import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.IDeleteOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderUseCaseImpl implements IDeleteOrderUseCase {
  @Autowired
  OrderRepository orderRepository;

  public void deleteOrder(String id) {
   var order = orderRepository.findOrderById(id);

    if (id != null) {
      orderRepository.delete(order);
    }
  }
}
