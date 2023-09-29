package com.crud.controller.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class ProductRequest {
  private Integer id;
  @NotBlank()
  private String name;
  private String description;
  @NotBlank()
  private Double price;
}
