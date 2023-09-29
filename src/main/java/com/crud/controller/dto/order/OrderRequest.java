package com.crud.controller.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderRequest {
  @NotBlank()
  private Integer customer;
}
