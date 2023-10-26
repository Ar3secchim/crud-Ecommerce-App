package com.crud.controller.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerResponse {
  private Integer id;
  private String name;
  private String email;
}
