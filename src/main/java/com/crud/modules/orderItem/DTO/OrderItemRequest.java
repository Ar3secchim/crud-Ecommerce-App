package com.crud.modules.orderItem.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class OrderItemRequest {
  @NotBlank()
  private String productSku;
  @NotBlank()
  private Integer amount;
}
