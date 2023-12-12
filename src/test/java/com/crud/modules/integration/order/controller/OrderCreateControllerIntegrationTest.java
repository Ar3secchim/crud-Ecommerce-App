package com.crud.modules.integration.order.controller;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OrderCreateControllerIntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void createOrderWithSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("int-test2");
    customer.setName("int-test");
    customer.setEmail("int-test2@gmail.com");
    customer.setAddress("int-test, 000");
    customer.setPassword("Int-test1");
    customerRepository.save(customer);

    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setCustomerId(customer.getIdTransaction());

    String order = mapper.writeValueAsString(orderRequest);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/order")
                    .content(order)
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

  @Test
  void createOrderWithCustomerInvalid() throws Exception {
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setCustomerId(null);

    String order = mapper.writeValueAsString(orderRequest);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/order")
                    .content(order)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isBadRequest()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.errors")
                    .value("Customer not found")
    );
  }
}
