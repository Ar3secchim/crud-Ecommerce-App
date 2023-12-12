package com.crud.modules.order.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderRequest {
  @NotBlank()
  private String customerId;
}
