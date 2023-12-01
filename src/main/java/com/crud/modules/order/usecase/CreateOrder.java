package com.crud.modules.order.usecase;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrder{
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CustomerRepository customerRepository;

  public OrderResponse execute(OrderRequest OrderRequest) {
    Customer customer = customerRepository.findById(OrderRequest.getCustomerSku()).get();
    Order order = OrderConvert.toEntity(customer);
    return OrderConvert.toResponseOrder(orderRepository.save(order));
  }
}
