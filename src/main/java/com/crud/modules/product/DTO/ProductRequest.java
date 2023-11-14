package com.crud.modules.product.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class ProductRequest {
  @Schema(name = "Product ID", example = "1", required = true)
  private String sku;

  @Schema(name = "name", example = "console-ps5", required = true)
  @NotBlank()
  private String name;

  @Schema(name = "description", example = "console ps5, 128gb...")
  private String description;

  @Schema(name = "price", example = "125.56", required = true)
  @NotBlank()
  private Double price;
}
