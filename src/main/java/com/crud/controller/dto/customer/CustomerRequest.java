package com.crud.controller.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRequest {
  private String id;
  private String name;
  private String email;
  private String address;
}
