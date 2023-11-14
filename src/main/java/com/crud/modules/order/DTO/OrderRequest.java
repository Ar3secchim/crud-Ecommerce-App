package com.crud.modules.order.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderRequest {
  @NotBlank()
  private String customerSku;
}
