package com.crud.controller.dto.OrderItem;

import com.crud.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderItemResponse {
  private Product product;
  private BigDecimal saleValue;
  private Integer amount;
}
