package com.crud.modules.integration.customers.controller;

import com.crud.modules.customers.DTO.CustomerRequestUpdate;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.usecase.UpdateCustomer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class CustomerUpdateControllerIntegrationTest {
  @MockBean
  private UpdateCustomer updateCustomer;

  @Autowired
  private MockMvc mockMvc;

  @Captor
  private ArgumentCaptor<CustomerRequestUpdate> captor;

  @Test
  void updateCustomerWithSuccess() throws Exception {
    String customerId = "int-test";
    Customer mockCustomer = new Customer();
    mockCustomer.setIdTransaction(customerId);
    mockCustomer.setEmail("int-test@gmail.com");



    mockMvc.perform(MockMvcRequestBuilders.put("/customer/int-test")
                    .content("""
                        {
                            "name": "John Doe",
                            "address": "123 Main Street"
                        }
                        """)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Main Street"));
  }

  @Test
  void updateCustomerWithNameInvalid() throws Exception {
    String customerId = "int-test";
    Customer mockCustomer = new Customer();
    mockCustomer.setIdTransaction(customerId);
    mockCustomer.setEmail("int-test@gmail.com");


    mockMvc.perform(MockMvcRequestBuilders.put("/customer/int-test")
                    .content("""
                        {
                            "name":"",
                            "address":"123 Main Street"
                        }
                        """)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(
                    MockMvcResultHandlers.print()
            )
            .andExpect(
                    MockMvcResultMatchers.status().is4xxClientError()
            );
  }

  @Test
  void updateCustomerWithNameCustomerNotExist() throws Exception {


    mockMvc.perform(MockMvcRequestBuilders.put("/customer/int-test")
                    .content("""
                        {
                            "name":"Joe Jon",
                            "address":"123 Main Street"
                        }
                        """)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(
                    MockMvcResultHandlers.print()
            )
            .andExpect(
                    MockMvcResultMatchers.status().is4xxClientError()
            );
  }
}
