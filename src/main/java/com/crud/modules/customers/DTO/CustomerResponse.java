package com.crud.modules.customers.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerResponse {
  private String sku;
  private String name;
  private String email;
}
