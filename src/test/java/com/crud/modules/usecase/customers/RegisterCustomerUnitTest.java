package com.crud.modules.usecase.customers;

import com.crud.infra.exception.PasswordValidationError;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;

import com.crud.modules.customers.usecase.RegisterCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RegisterCustomerUnitTest {
  @Mock
  private CustomerRepository repository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private RegisterCustomer registerCustomer;

  private Customer customer;

  @BeforeEach
  public void setup(){
    customer = new Customer();
    customer.setIdTransaction(UUID.randomUUID().toString());
    customer.setEmail("unit-test@email.com");
    customer.setAddress("unit-test-address,999");
    customer.setName("unit test");
    customer.setPassword("@UnitTest123");

    when(passwordEncoder.encode(customer.getPassword())).thenReturn(customer.getPassword());
    when(repository.findByEmail("unit-test@email.com")).thenReturn(null);
  }


  @Test
  @DisplayName("should register customer when everything success")
  public void registerCustomerWithSuccess() throws Exception {
    registerCustomer.execute(customer);

    verify(repository, times(1)).save(any());
    verify(passwordEncoder, times(1)).encode(any());
  }

  @Test
  @DisplayName("should throw exception customer when name is invalid")
  public void registerCustomerWithNameLessThanTwoCharacters() {
    customer.setName("ut");

    Exception exception = assertThrows(
            Exception.class, () -> {
              registerCustomer.execute(customer);
            }
    );

   assertEquals("length must be between 3 and 35", exception.getMessage());
  }

  @Test
  @DisplayName("should throw exception customer when email is invalid")
  public void registerCustomerWithEmailInvalid() {
    customer.setEmail("emailInvalid");

    Exception exeption = assertThrows(
            Exception.class, () -> registerCustomer.execute(customer));

    assertEquals("must be a well-formed email address", exeption.getMessage());
  }

  @Test
  @DisplayName("should throw exception customer when password is invalid")
  public void registerCustomerWithPasswordInvalid() {
    customer.setPassword("12345");

    PasswordValidationError exeption = assertThrows(
            PasswordValidationError.class, () -> registerCustomer.execute(customer));

    assertEquals("Senha deve seguir o padrão", exeption.getMessage());
  }

  @Test
  @DisplayName("should customer when email is exist")
  public void registerCustomerWithEmailExist() throws Exception {
    when(repository.findByEmail("unit-test@email.com")).thenReturn(customer);

    Exception exception = assertThrows(
            Exception.class, () -> {
              registerCustomer.execute(customer);
            }
    );
    assertEquals("Email já está em uso", exception.getMessage());
  }

  @Test
  @DisplayName("should customer when password is cryptographic")
  public void registerCustomerWithPasswordEncode() throws Exception {
    registerCustomer.execute(customer);
    verify(passwordEncoder, times(1)).encode(any());
  }
}
