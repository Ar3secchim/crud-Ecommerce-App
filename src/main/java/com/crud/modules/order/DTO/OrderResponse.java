package com.crud.modules.order.DTO;

import com.crud.modules.order.entity.Order;
import com.crud.modules.orderItem.DTO.OrderItemResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
  private String idTransaction;
  private String customer;
  private List<OrderItemResponse> items;
  private Order.OrderStatus status;
  private BigDecimal total;
}
