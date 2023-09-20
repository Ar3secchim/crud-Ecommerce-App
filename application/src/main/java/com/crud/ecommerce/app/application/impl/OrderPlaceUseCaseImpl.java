package com.crud.ecommerce.app.application.impl;

import com.crud.ecommerce.app.application.INotifierOrderUserCase;
import com.crud.ecommerce.app.application.IOrderPlaceUseCase;
import com.crud.ecommerce.app.application.repository.OrderRepository;
import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderItem;
import com.crud.ecommerce.app.domain.OrderStatus;

import java.math.BigDecimal;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {
  private final INotifierOrderUserCase notifierUseCaseEmail;
  private final INotifierOrderUserCase notifierUseCaseSms;


  public OrderPlaceUseCaseImpl(
          OrderRepository orderRepository,
          INotifierOrderUserCase notifierUseCaseEmail,
          INotifierOrderUserCase notifierUseCaseSms
  ) {
    this.notifierUseCaseEmail = notifierUseCaseEmail;
    this.notifierUseCaseSms = notifierUseCaseSms;
  }

  @Override
  public void placeOrder(Order order) {
    if (order.getStatus() != OrderStatus.OPEN) {
      throw new IllegalStateException("O pedido não está aberto");
    }

    if (order.getItems() == null || order.getItems().isEmpty()) {
      throw new IllegalStateException("Não há produtos no carrinho");
    }

    BigDecimal totalValue = calculateTotalValue(order);
    //Integer totalValueCompareTo = totalValue.compareTo(BigDecimal.ZERO);
    if (totalValue.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalStateException("Adicione produtos ao carrinho antes de fechar o pedido");
    }

    order.setStatus(OrderStatus.PENDING_PAYMENT);
    notifierUseCaseEmail.pendingPayment(order);
    notifierUseCaseSms.pendingPayment(order);
  }

  private BigDecimal calculateTotalValue(Order order) {
    BigDecimal totalValue = BigDecimal.ZERO;

    for (OrderItem item : order.getItems()) {
      totalValue = totalValue.add(item.getSaleValue());
    }

    return totalValue;
  }
}
