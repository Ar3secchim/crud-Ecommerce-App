package com.crud.ecommerce.app.application;

import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.OrderItem;
import com.crud.ecommerce.app.domain.Product;

import java.math.BigDecimal;

public interface IOrderAddItemUseCase {
  /*
   * 1 - Pedido precisa estar com status == OrderStatus.OPEN
   * 2 - Lembrar de atualizar o banco através do repository
   */
  OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount);
}
