package com.crud.modules.customers.usecase;

import com.crud.infra.exception.ValidationError;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DeleteCustomerUnitTest {
  @Mock
  private CustomerRepository repository;
  @InjectMocks
  private DeleteCustomer deleteCustomer;

  @Test
  @DisplayName("Should customer delete success")
  public void deleteCustomerSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setSku(UUID.randomUUID().toString());

    when(repository.findById(customer.getSku())).thenReturn(Optional.of(customer));

    deleteCustomer.execute(customer.getSku());

    verify(repository, times(1)).delete(customer);
  }

  @Test
  @DisplayName("Should exception a delete customer not exist")
  public void deleteCustomerEquals(){
    EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class, () -> deleteCustomer.execute("unit-test"));

    assertEquals("Customer not found with ID: unit-test", exception.getMessage());
  }

}
