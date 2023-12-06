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

  public OrderResponse execute(String orderId, Order orderInput) throws Exception {
    Order order = orderRepository.findOrderById(orderId);

    if(order == null) throw new Exception("Order not found");

    order.setStatus(orderInput.getStatus());
    order.setUpdatedAt(LocalDateTime.now());
    order.setOrderItens(orderInput.getOrderItens());
    order.setTotal(CalculateTotal.execute(orderInput));

    orderRepository.save(order);

    return OrderConvert.toResponseOrder(order);
  }
}
