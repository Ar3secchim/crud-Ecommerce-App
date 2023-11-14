package com.crud.modules.order.usecase.impl;

import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.IUpdateOrderUseCase;
import com.crud.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateOrderUseCaseImpl implements IUpdateOrderUseCase {
  @Autowired
  OrderRepository orderRepository;

  @Override
  public OrderResponse update(String orderId, Order orderRequest) {
    Order Order = orderRepository.findOrderById(orderId);

    Order.setStatus(orderRequest.getStatus());
    Order.setUpdatedAt(LocalDateTime.now());
    Order.setTotal(orderRequest.getTotal());
    Order.setOrderItens(orderRequest.getOrderItens());

    Order updatedOrder = orderRepository.save(Order);

    return OrderConvert.toResponseOrder(updatedOrder);
  }
}
