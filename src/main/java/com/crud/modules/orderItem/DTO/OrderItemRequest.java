package com.crud.modules.orderItem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemRequest {
  @NotBlank()
  private String productId;
  @NotBlank()
  private Integer amount;
}
