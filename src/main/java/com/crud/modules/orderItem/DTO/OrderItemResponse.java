package com.crud.modules.orderItem.DTO;

import com.crud.modules.product.DTO.ProductResponse;
import lombok.Data;

@Data
public class OrderItemResponse {
  private String OrderSku;
  private ProductResponse product;
  private Integer amount;
  private Double total;
}
