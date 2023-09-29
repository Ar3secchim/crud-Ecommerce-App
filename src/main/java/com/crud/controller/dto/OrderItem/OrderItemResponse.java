package com.crud.controller.dto.OrderItem;

import com.crud.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderItemResponse {
  private Integer order;
  private Product product;
  private Double total;
}
