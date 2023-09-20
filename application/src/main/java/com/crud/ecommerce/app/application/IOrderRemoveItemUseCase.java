package com.crud.ecommerce.app.application;

import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.Product;

public interface IOrderRemoveItemUseCase {

  /*
   * 1 - Pedido precisa estar com status == OrderStatus.OPEN
   * 2 - Lembrar de atualizar o banco através do repository
   */
  void removeItem(Order order, Product product);
}
