package com.crud.controller.dto.OrderItem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderItemRequest {
  @NotBlank()
  private Integer product;
  @NotBlank()
  private Integer amount;
}
