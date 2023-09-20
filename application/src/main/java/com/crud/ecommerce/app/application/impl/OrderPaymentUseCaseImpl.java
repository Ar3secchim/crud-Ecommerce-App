package com.crud.ecommerce.app.application.impl;

import com.crud.ecommerce.app.application.INotifierOrderUserCase;
import com.crud.ecommerce.app.application.IOrderPaymentUseCase;
import com.crud.ecommerce.app.application.repository.OrderRepository;
import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderStatus;

public class OrderPaymentUseCaseImpl implements IOrderPaymentUseCase {
  private final INotifierOrderUserCase notifierUseCaseEmail;
  private final INotifierOrderUserCase notifierUseCaseSms;
  private OrderRepository orderRepository;


  public OrderPaymentUseCaseImpl(
          OrderRepository repository,
          INotifierOrderUserCase notifierUseCaseEmail,
          INotifierOrderUserCase notifierUseCaseSms
  ) {
    this.orderRepository = repository;
    this.notifierUseCaseEmail = notifierUseCaseEmail;
    this.notifierUseCaseSms = notifierUseCaseSms;
  }

  @Override
  public void pay(Order order) {
    if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
      throw new IllegalStateException("O pedido não pode ser pago");
    }

    order.setStatus(OrderStatus.PAID);
    orderRepository.save(order);
    notifierUseCaseEmail.updatedPayOrder(order);
    notifierUseCaseSms.updatedPayOrder(order);
  }
}
