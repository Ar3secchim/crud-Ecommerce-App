package com.crud.modules.product.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class ProductResponse {
  @Schema(name = "Product ID", example = "1", required = true)
  private String sku;

  @Schema(name = "name", example = "console-ps5", required = true)
  private String name;

  @Schema(name = "price", example = "125.56", required = true)
  private Double price;
}
