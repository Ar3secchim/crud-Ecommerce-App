package com.crud.ecommerce.app.application.impl;

import com.crud.ecommerce.app.application.INotifierOrderUserCase;
import com.crud.ecommerce.app.application.IOrderShippingUseCase;
import com.crud.ecommerce.app.application.repository.OrderRepository;
import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderStatus;

public class OrderShippingUseCaseImpl implements IOrderShippingUseCase {

  private final OrderRepository orderRepository;
  private final INotifierOrderUserCase notifierUseCaseEmail;
  private final INotifierOrderUserCase notifierUseCaseSms;


  public OrderShippingUseCaseImpl(
          OrderRepository orderRepository,
          INotifierOrderUserCase notifierUseCaseEmail,
          INotifierOrderUserCase notifierUseCaseSms
  ) {
    this.orderRepository = orderRepository;
    this.notifierUseCaseEmail = notifierUseCaseEmail;
    this.notifierUseCaseSms = notifierUseCaseSms;
  }

  @Override
  public void shipping(Order order) {
    if (order.getStatus() != OrderStatus.PAID) {
      throw new IllegalStateException("O pedido não pode ser pago");
    }

    order.setStatus(OrderStatus.FINISH);
    orderRepository.save(order);
    notifierUseCaseEmail.shipping(order);
    notifierUseCaseSms.shipping(order);
  }

}
