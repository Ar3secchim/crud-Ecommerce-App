package com.crud.ecommerce.app.application.impl;

import com.crud.ecommerce.app.application.IOrderAddItemUseCase;
import com.crud.ecommerce.app.application.repository.OrderRepository;
import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderItem;
import com.crud.ecommerce.app.domain.OrderStatus;
import com.crud.ecommerce.app.domain.Product;

import java.math.BigDecimal;

public class OrderAddItemUseCaseImpl implements IOrderAddItemUseCase {
  private final OrderRepository orderRepository;

  public OrderAddItemUseCaseImpl(
          OrderRepository repository
  ) {
    this.orderRepository = repository;
  }

  @Override
  public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
    if (order.getStatus() != OrderStatus.OPEN) {
      throw new IllegalStateException("Não pode adicionar item no pedido");
    }

    OrderItem newItem = createOrderItem(product, price, amount);
    order.getItems().add(newItem);

    orderRepository.update(order);
    return newItem;
  }

  private OrderItem createOrderItem(Product product, BigDecimal price, Integer amount) {
    OrderItem item = new OrderItem();

    item.setProduct(product);
    item.setSaleValue(price);
    item.setAmount(amount);
    return item;
  }
}
