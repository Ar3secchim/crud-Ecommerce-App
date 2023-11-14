package com.crud.modules.order.usecase.impl;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.ICreateOrderUseCase;
import com.crud.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCaseImpl implements ICreateOrderUseCase {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CustomerRepository customerRepository;

  @Override
  public OrderResponse create(OrderRequest OrderRequest) {
    Customer customer = customerRepository.findCustomerById(OrderRequest.getCustomerSku());
    Order order = OrderConvert.toEntity(customer);
    return OrderConvert.toResponseOrder(orderRepository.save(order));
  }
}
