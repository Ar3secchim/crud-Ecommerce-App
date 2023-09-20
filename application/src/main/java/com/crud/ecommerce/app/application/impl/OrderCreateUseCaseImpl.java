package com.crud.ecommerce.app.application.impl;

import com.crud.ecommerce.app.application.ICreateOrderUseCase;
import com.crud.ecommerce.app.application.repository.CustomerRepository;
import com.crud.ecommerce.app.application.repository.OrderRepository;
import com.crud.ecommerce.app.domain.Customer;
import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderCreateUseCaseImpl implements ICreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;

  public OrderCreateUseCaseImpl(OrderRepository repository, CustomerRepository customerRepository) {
    this.orderRepository = repository;
    this.customerRepository = customerRepository;
  }

  private void validCustomer(Customer customer) {
    Customer found = customerRepository.findByDocument(customer.getDocument());
    if (found == null) {
      throw new IllegalStateException("Cliente não encontrado");
    }
  }

  private Order createNewOrder(Customer customer) {
    Order order = new Order();
    order.setCustomer(customer);
    order.setItems(new ArrayList<>());
    order.setStatus(OrderStatus.OPEN);
    order.setShippingAddress("Minha casa sempre");
    order.setOrderedAt(LocalDateTime.now());

    return order;
  }

  @Override
  public Order create(Customer customer) {
    validCustomer(customer);

    Order newOrder = createNewOrder(customer);

    orderRepository.save(newOrder);
    return newOrder;
  }
}
