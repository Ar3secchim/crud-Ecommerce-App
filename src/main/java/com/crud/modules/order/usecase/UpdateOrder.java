package com.crud.modules.order.usecase;


import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.utils.CalculateTotal;
import com.crud.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateOrder{
  @Autowired
  OrderRepository orderRepository;

  CalculateTotal calculateTotal = new CalculateTotal();

  public OrderResponse execute(String orderId, Order orderInput) {
    Order Order = orderRepository.findOrderById(orderId);

    Order.setStatus(orderInput.getStatus());
    Order.setUpdatedAt(LocalDateTime.now());
    Order.setTotal(calculateTotal.calculateNewTotal(orderInput));
    Order.setOrderItens(orderInput.getOrderItens());

    Order updatedOrder = orderRepository.save(Order);

    return OrderConvert.toResponseOrder(updatedOrder);
  }
}
