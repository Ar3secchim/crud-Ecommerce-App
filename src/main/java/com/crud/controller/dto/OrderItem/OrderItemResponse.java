package com.crud.controller.dto.OrderItem;

import com.crud.controller.dto.product.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemResponse {
  private Integer OrderId;
  private ProductResponse product;
  private Integer amount;
  private Double total;
}
