package com.crud.modules.orderItem.DTO;

import com.crud.modules.product.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
  private String OrderSku;
  private Product product;
  private Integer amount;
  private BigDecimal total;
}
