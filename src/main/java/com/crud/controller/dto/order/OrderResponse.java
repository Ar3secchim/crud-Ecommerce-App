package com.crud.controller.dto.order;

import com.crud.controller.dto.OrderItem.OrderItemResponse;
import com.crud.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class OrderResponse {
  private Integer id;
  private Integer customer;
  private List<OrderItemResponse> items;
  private OrderStatus status;
}
