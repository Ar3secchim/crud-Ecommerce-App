package com.crud.modules.integration.customers.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.usecase.DeleteCustomer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerDeleteControllerIntegrationTest {
  @MockBean
  private DeleteCustomer deleteCustomer;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void customerGetAllWithSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction(UUID.randomUUID().toString());

    mockMvc.perform(
            MockMvcRequestBuilders.delete("/customer/" + customer.getIdTransaction())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isNoContent()
    );
    verify(deleteCustomer, times(1)).execute(customer.getIdTransaction());
  }

  @Test
  void testDeleteCustomerNotFound() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction(UUID.randomUUID().toString());

    doThrow(new BadRequestClient("Customer not found with ID: " + customer.getIdTransaction()))
            .when(deleteCustomer).execute(customer.getIdTransaction());

    mockMvc.perform(
            MockMvcRequestBuilders.delete("/customer/" + customer.getIdTransaction())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is4xxClientError()
    ).andExpect(MockMvcResultMatchers.jsonPath("$.errors")
            .value("Customer not found with ID: " + customer.getIdTransaction()));
  }
}
