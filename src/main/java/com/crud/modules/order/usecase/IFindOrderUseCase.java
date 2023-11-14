package com.crud.modules.order.usecase;

import com.crud.modules.order.DTO.OrderResponse;

import java.util.List;

public interface IFindOrderUseCase {
  List<OrderResponse> findAll();
  OrderResponse findById(String orderId);
}
