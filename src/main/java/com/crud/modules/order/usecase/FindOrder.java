package com.crud.modules.order.usecase;

import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.utils.OrderConvert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrder {
  private final OrderRepository orderRepository;

  public List<OrderResponse> findAll() {
    return OrderConvert.toResponseList(orderRepository.findAll());
  }

  public OrderResponse findById(String orderId) throws Exception {
    Order order = orderRepository.findOrderById(orderId);

    if (order == null) throw new Exception("Order not found");

    return OrderConvert.toResponseOrder(order);
  }
}
