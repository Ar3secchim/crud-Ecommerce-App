package com.crud.controller.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class ProductRequest {
  private Integer id;
  private String barcode;
  private String description;
  private BigDecimal price;
}
