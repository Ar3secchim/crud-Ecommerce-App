package com.crud.modules.order.usecase;

import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;

public interface IUpdateOrderUseCase {
  OrderResponse update(String orderId, Order OrderRequest);
}
