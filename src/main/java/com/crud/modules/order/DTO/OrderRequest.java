package com.crud.modules.order.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class OrderRequest {
  @NotBlank()
  private String customerSku;
}
