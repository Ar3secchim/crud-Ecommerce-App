package com.crud.modules.usecase.customers;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.FindCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    customer.setIdTransaction("unit-test");
    customer.setEmail("validEmail@email.com");
    customer.setAddress("validAddress,999");
    customer.setName("ValidName");
    customer.setPassword("@validPassword123");
  }

  @Test
  @DisplayName("Should customer find by id")
  public void findCustomerById() throws BadRequestClient {
    when(repository.findByIdTransaction(customer.getIdTransaction())).thenReturn(customer);

    CustomerResponse customerTest = findCustomer.findById(customer.getIdTransaction());

    verify(repository, times(1)).findByIdTransaction(any());
    assertEquals(customer.getIdTransaction(), customerTest.getIdTransaction(), "Unexpected customer id");
  }

  @Test
  @DisplayName("Should customer find by id invalid")
  public void findCustomerByIdInvalid(){
    when(repository.findByIdTransaction(customer.getIdTransaction())).thenReturn(null);

    var execption = assertThrows(Exception.class, () -> findCustomer.findById(customer.getIdTransaction()));

    assertEquals("Customer not found with ID: " + "unit-test", execption.getMessage());
    verify(repository, times(1)).findByIdTransaction(any());
  }

  @Test
  @DisplayName("Should customer find by email")
  public void findCustomerByEmail() throws BadRequestClient {
    when(repository.findByEmail(customer.getEmail())).thenReturn(customer);

    CustomerResponse customerTest = findCustomer.findByEmail(customer.getEmail());

    verify(repository, times(1)).findByEmail(any());
    assertEquals("validEmail@email.com", customerTest.getEmail(), "Unexpected customer email");
  }

  @Test
  @DisplayName("Should customer find by email invalid")
  public void findCustomerByEmailInvalid(){
    when(repository.findByEmail(customer.getEmail())).thenReturn(null);

    var execption = assertThrows(Exception.class, () -> findCustomer.findByEmail(customer.getEmail()));
    assertEquals("Customer not found with email: " + "validEmail@email.com", execption.getMessage());
    verify(repository, times(1)).findByEmail(any());
  }

  @Test
  @DisplayName("Should customer find by name")
  public void findCustomerByName() throws BadRequestClient {
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

  @Test
  @DisplayName("Should customer find by name for equals null")
  public void findCustomerByNameEqualsNull(){
    when(repository.findByName(null)).thenReturn(null);

    var execption = assertThrows(Exception.class, () ->  findCustomer.findByName(null));

    assertEquals("Name not null", execption.getMessage());
  }
}
