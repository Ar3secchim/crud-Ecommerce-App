package com.crud.modules.orderItem.usecase;

import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.DTO.OrderItemResponse;

public interface IAddItemOrderItemUseCase {
  OrderItemResponse addItem(String orderId, OrderItemRequest orderItemRequest);
}
