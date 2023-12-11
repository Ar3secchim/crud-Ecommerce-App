package com.crud.modules.integration.order.controller;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderRequest;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.usecase.CreateOrder;
import com.crud.utils.CustomerConvert;
import com.crud.utils.OrderConvert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderCreateControllerIntegrationTest {
  @MockBean
  private CreateOrder createOrder;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createOrderWithSuccess() throws Exception {
    Order order = new Order();

    OrderRequest request = new OrderRequest();
    request.setCustomerId("unit-test");

    Customer customer = new Customer();
    customer.setIdTransaction("unit-test");

    // mock
    when(createOrder.execute(request)).thenReturn(OrderConvert.toResponseOrder(order));
    when(customerRepository.findByIdTransaction("unit-test")).thenReturn(customer);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/order")
              .content("""
                 {
                  "customerId": "int-test",
                 }
               """)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isCreated()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.idTransaction").isNotEmpty()
    );

  }
}
