package com.crud.modules.customers.usecase;

import com.crud.infra.exception.PasswordValidationError;
import com.crud.infra.exception.ValidationError;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.mockito.Mockito.*;


class RegisterCustomerTest {
  private CustomerRepository repository;
  private PasswordEncoder passwordEncoder;
  private RegisterCustomer registerCustomer;
  private Customer customer;

  @BeforeEach
  public void setup(){
    repository = Mockito.mock(CustomerRepository.class);
    passwordEncoder = Mockito.mock(PasswordEncoder.class);

    customer = new Customer();
    customer.setSku(UUID.randomUUID().toString());
    customer.setEmail("validEmail@email.com");
    customer.setAddress("validAddress,999");
    customer.setName("ValidName");
    customer.setPassword("@validPassword123");

    registerCustomer = new RegisterCustomer(repository, passwordEncoder);
  }


  @Test
  @DisplayName("should register customer when everything success")
  public void registerCustomerWithSuccess() throws PasswordValidationError, ValidationError {

    when(passwordEncoder.encode(customer.getPassword())).thenReturn(customer.getPassword());

    registerCustomer.execute(customer);

    verify(repository, times(1)).save(any());
    verify(passwordEncoder, times(1)).encode(any());
  }

  @Test
  @DisplayName("should throw exception customer when name is invalid")
  public void registerCustomerWithNameLessThanTwoCharacters() {
    customer.setName("As");
    when(passwordEncoder.encode(customer.getPassword())).thenReturn(customer.getPassword());

    ValidationError exeption = Assertions.assertThrows(
            ValidationError.class, () -> {
              registerCustomer.execute(customer);
            }
    );

    Assertions.assertEquals("Nome menor que dois character", exeption.getMessage());
  }

  @Test
  @DisplayName("should throw exception customer when email is invalid")
  public void registerCustomerWithEmailInvalid() {
    customer.setEmail("emailInvalid.com");
    when(passwordEncoder.encode(customer.getPassword())).thenReturn(customer.getPassword());

    ValidationError exeption = Assertions.assertThrows(
            ValidationError.class, () -> {
              registerCustomer.execute(customer);
            }
    );

    Assertions.assertEquals("Email inválido", exeption.getMessage());
  }

  @Test
  @DisplayName("should throw exception customer when password is invalid")
  public void registerCustomerWithPasswordInvalid() {
    customer.setPassword("12345");

    when(passwordEncoder.encode(customer.getPassword())).thenReturn(customer.getPassword());
    PasswordValidationError exeption = Assertions.assertThrows(
            PasswordValidationError.class, () -> {
              registerCustomer.execute(customer);
            }
    );

    Assertions.assertEquals("Senha deve seguir o padrão", exeption.getDescription());
  }

  @Test
  @DisplayName("should customer when password is cryptographic")
  public void registerCustomerWithPasswordEncode() throws PasswordValidationError, ValidationError {
    registerCustomer.execute(customer);
    verify(passwordEncoder, times(1)).encode(any());
  }
}
