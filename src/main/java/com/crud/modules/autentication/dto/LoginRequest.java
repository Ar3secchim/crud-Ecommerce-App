package com.crud.modules.autentication.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class LoginRequest {
  @Email
  private String email;
  private String password;
}
