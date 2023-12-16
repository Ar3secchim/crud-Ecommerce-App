package com.crud.modules.usecase.autentication;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.autentication.usecase.AutenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AutenticationServiceUnitTest {
  @Mock
  private CustomerRepository repository;
  @InjectMocks
  private AutenticationService autenticationService;

  @Test
  @DisplayName("Should authentication user with success")
  void AuthenticationUserSuccess() throws BadRequestClient {
    Customer customer = new Customer();
    customer.setEmail("uni-Test@email.com");

    when(repository.findByEmail("uni-Test@email.com")).thenReturn(customer);

    Customer customerValid = autenticationService.loadUserByUsername("uni-Test@email.com");
    assertEquals("uni-Test@email.com", customerValid.getEmail());
  }

  @Test
  @DisplayName("Should authentication user invalid and return exception")
  void AuthenticationUserInvalid(){
    when(repository.findByEmail("uni-test@email.com")).thenReturn(null);

    var exeception = assertThrows(RuntimeException.class, () ->
            autenticationService.loadUserByUsername("uni-test@email.com"));

    assertEquals("Customer not found", exeception.getMessage());
  }
}
