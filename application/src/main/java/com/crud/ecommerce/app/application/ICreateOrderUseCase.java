package com.crud.ecommerce.app.application;

import com.crud.ecommerce.app.domain.Order;
import com.crud.ecommerce.app.domain.Customer;

public interface ICreateOrderUseCase {
  /*
   * 1 - Inicia um novo pedido para o cliente
   * 2 - Pedido deve iniciar com status igual a OrderStatus.OPEN
   * 3 - Lembrar de atualizar o banco através do repository
   */
  Order create(Customer customer);
}
