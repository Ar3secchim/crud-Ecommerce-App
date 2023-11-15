package com.crud.modules.order.usecase;

import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.utils.OrderConvert;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindOrder {
  @Autowired
  OrderRepository orderRepository;

  public List<OrderResponse> findAll() {
    return OrderConvert.toResponseList(orderRepository.findAll());
  }

  @Transactional
  public OrderResponse findById(String orderId){
    return OrderConvert.toResponseOrder(orderRepository.findOrderById(orderId));
  }
}