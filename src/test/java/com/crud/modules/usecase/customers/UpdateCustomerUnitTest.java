package com.crud.modules.usecase.customers;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.UpdateCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class UpdateCustomerUnitTest {
  @Mock
  private CustomerRepository repository;
  @InjectMocks
  private UpdateCustomer updateCustomer;

  @Test
  @DisplayName("Should update customer success")
  public void UpdateCustomerSuccess() throws Exception {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setSku("uni-test");
    customerRequest.setEmail("unit@teste.com");
    customerRequest.setName("unit-test");
    customerRequest.setAddress("street uni test, 000");

    Customer customer = new Customer();
    customer.setIdTransaction("uni-test");

    when(repository.findByIdTransaction("uni-test")).thenReturn(customer);

    CustomerResponse customerResponse = updateCustomer.execute(customerRequest.getSku(), customerRequest);

    verify(repository, times(1)).save(any());
    assertEquals("unit-test", customerResponse.getName());
  }

  @Test
  @DisplayName("Should update product invalid")
  void UpdateProductIdInvalid(){
    CustomerRequest customerRequest = new CustomerRequest();

    when(repository.findByIdTransaction("uni-test")).thenReturn(new Customer());

    Customer customer = repository.findByIdTransaction("uni-test");

    Exception exception = assertThrows(
            Exception.class, () -> updateCustomer.execute(customer.getIdTransaction(), customerRequest));
    assertEquals("Customer not found", exception.getMessage());
  }
}
