package com.crud.modules.order.usecase;

import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.DTO.OrderResponse;

public interface ICreateOrderUseCase {
  OrderResponse create(OrderRequest OrderRequest);
}
