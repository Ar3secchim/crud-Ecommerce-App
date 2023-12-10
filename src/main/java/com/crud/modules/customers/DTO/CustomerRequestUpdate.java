package com.crud.modules.customers.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerRequestUpdate {
  @NotBlank
  @Length(min= 3, max= 35)
  private String name;
  private String address;
}
