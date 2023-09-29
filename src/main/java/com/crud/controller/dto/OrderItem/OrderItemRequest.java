package com.crud.controller.dto.OrderItem;

import com.crud.model.Product;
import lombok.Getter;

@Getter
public class OrderItemRequest {
  private Integer product;
  private Integer amount;
}
