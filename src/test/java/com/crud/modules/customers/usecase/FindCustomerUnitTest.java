package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FindCustomerUnitTest {
  @Mock
  private CustomerRepository repository;
  @InjectMocks
  private FindCustomer findCustomer;

  private Customer customer;

  @BeforeEach
  public void setup(){
    customer = new Customer();
    customer.setSku(UUID.randomUUID().toString());
    customer.setEmail("validEmail@email.com");
    customer.setAddress("validAddress,999");
    customer.setName("ValidName");
    customer.setPassword("@validPassword123");
  }

  @Test
  @DisplayName("Should customer find by id")
  public void findCustomerById(){
    when(repository.findById(customer.getSku())).thenReturn(Optional.ofNullable(customer));

    CustomerResponse customerTest = findCustomer.findById(customer.getSku());

    verify(repository, times(1)).findById(any());
    assertEquals(customer.getSku(), customerTest.getSku(), "Unexpected customer id");
  }

  @Test
  @DisplayName("Should customer find by email")
  public void findCustomerByEmail(){
    when(repository.findByEmail(customer.getEmail())).thenReturn(customer);

    CustomerResponse customerTest = findCustomer.findByEmail(customer.getEmail());

    verify(repository, times(1)).findByEmail(any());
    assertEquals("validEmail@email.com", customerTest.getEmail(), "Unexpected customer email");
  }

  @Test
  @DisplayName("Should customer find by name")
  public void findCustomerByName(){
    ArrayList<Customer> listCustomer = new ArrayList<>();
    Customer customerTest = new Customer();

    for (int i = 0; i < 4; i++) {
      customerTest.setName("name " + i);
      customerTest.setEmail("email" + i+ "@email.com");
      listCustomer.add(customerTest);
    }

    when(repository.findByName("name")).thenReturn(listCustomer);

    List<CustomerResponse> result = findCustomer.findByName("name");

    verify(repository, times(1)).findByName(any());

    for (CustomerResponse currentCustomer : result) {
      assertTrue(currentCustomer.getName().contains("name"), "Unexpected customer name");
    }
  }
}
