package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ListCustomerUnitTest {
  @Mock
  private CustomerRepository repository;
  @InjectMocks
  private ListCustomer listCustomer;

  @Test
  @DisplayName("should all customer")
  void ListAllCustomer(){
    ArrayList<Customer> listCustomers = new ArrayList<>();
    Customer customerTest = new Customer();

    for (int i = 0; i < 4; i++) {
      customerTest.setName("name " + i);
      customerTest.setEmail("email" + i+ "@email.com");
      listCustomers.add(customerTest);
    }

    when(repository.findAll()).thenReturn(listCustomers);

    List<CustomerResponse> listAllCustomer = listCustomer.execute();

    verify(repository, times(1)).findAll();
    assertEquals(4, listAllCustomer.size(), "Unexpected customer name");

  }
}
