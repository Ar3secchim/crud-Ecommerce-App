package com.crud.modules.integration.customers.controller;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.usecase.RegisterCustomer;
import com.crud.utils.CustomerConvert;
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

@SpringBootTest
@AutoConfigureMockMvc
class CustomerCreateControllerIntegrationTest {
  @MockBean
  private RegisterCustomer registerCustomer;;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setup() throws Exception {
    Mockito.doAnswer(invocationOnMock -> {
      Customer customer = (Customer) invocationOnMock.getArgument(0);
      customer.setIdTransaction(UUID.randomUUID().toString());
      return CustomerConvert.toResponse(customer);
    }).when(registerCustomer).execute(Mockito.any());
  }

  @Test
  void createCustomerWithSuccess() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.post("/customer")
                    .content("""
                            {
                              "name": "int-test",
                              "email": "int-test@gmail.com",
                              "address": "int-test, 000",
                              "password":"@Int-test123"
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

  @Test
  void createCustomerWithNameErrorLengthMust3() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.post("/customer")
                    .content("""
                            {
                              "name": "t",
                              "email": "int-test@gmail.com",
                              "address": "int-test, 000",
                              "password":"@Int-test123"
                            }
                            """)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is4xxClientError()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.[0].message")
                    .value("length must be between 3 and 35")
    );
  }

  @Test
  void createCustomerWithEmailError() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.post("/customer")
                    .content("""
                            {
                              "name": "int-test",
                              "email": "int-test",
                              "address": "int-test, 000",
                              "password":"@Int-test123"
                            }
                            """)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is4xxClientError()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.[0].message")
                    .value("must be a well-formed email address")
    );
  }

}
