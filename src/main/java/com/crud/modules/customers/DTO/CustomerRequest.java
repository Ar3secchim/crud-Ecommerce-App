package com.crud.modules.customers.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class CustomerRequest {
  private String sku;
  @NotBlank
  @Length(min= 3, max= 35)
  private String name;
  @Email
  private String email;
  private String address;
  private String password;
}
