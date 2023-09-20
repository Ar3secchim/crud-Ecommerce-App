package com.crud.ecommerce.app.application;

import com.crud.ecommerce.app.domain.Order;

public interface INotifierOrderUserCase {

  void shipping(Order order);

  void updatedPayOrder(Order order);

  void pendingPayment(Order order);
}
