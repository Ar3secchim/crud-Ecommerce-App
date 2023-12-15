package com.crud.modules.integration.customers.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DTO.CustomerRequestUpdate;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.customers.usecase.UpdateCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;


@SpringBootTest
@AutoConfigureMockMvc
public class CustomerUpdateControllerIntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private UpdateCustomer updateCustomer;

  @Test
  void updateCustomerWithSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("int-test");
    customer.setName("int test5");
    customer.setEmail("int-test5@gmail.com");
    customer.setAddress("int test, 000");
    customer.setPassword("Int-test5");
    customerRepository.save(customer);

    CustomerRequestUpdate customerUpdate = new CustomerRequestUpdate();
    customerUpdate.setName("John Doe");
    customerUpdate.setAddress("123 Main Street");

    String customerRequest = mapper.writeValueAsString(customerUpdate);

    mockMvc.perform(MockMvcRequestBuilders.put("/customer/" + customer.getIdTransaction())
                    .content(customerRequest)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(
                    MockMvcResultMatchers.status().isOk()
            );
  }

  @Test
  void updateCustomerWithNameCustomerNotExist() throws Exception {
    doThrow(new BadRequestClient("Customer not found"))
            .when(updateCustomer).execute(any(), any());

    mockMvc.perform(MockMvcRequestBuilders.put("/customer/null")
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
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.errors")
                            .value("Customer not found")
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.code")
                            .value("BAD_REQUEST")
            );
  }
}
