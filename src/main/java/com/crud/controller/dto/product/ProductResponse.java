package com.crud.controller.dto.product;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class ProductResponse {
  private Integer id;
  private String name;
  private Double price;
}
