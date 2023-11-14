package com.crud.modules.orderItem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderItemRequest {
  @NotBlank()
  private String productSku;
  @NotBlank()
  private Integer amount;
}
