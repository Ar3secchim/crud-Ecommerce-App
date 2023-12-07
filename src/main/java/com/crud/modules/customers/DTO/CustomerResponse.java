package com.crud.modules.customers.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerResponse {
  private String idTransaction;
  private String name;
  private String email;
}
