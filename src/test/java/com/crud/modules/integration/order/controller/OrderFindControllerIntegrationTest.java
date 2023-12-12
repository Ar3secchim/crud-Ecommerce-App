package com.crud.modules.integration.order.controller;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.DTO.OrderResponse;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.FindOrder;
import com.crud.utils.OrderConvert;
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

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderFindControllerIntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  OrderRepository orderRepository;

  @MockBean
  private FindOrder FindOrder;

  @Autowired
  private MockMvc mockMvc;
  
  @Test
  void orderGetAllWithSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("customer-int");
    customer.setName("int-test");
    customer.setEmail("int-test@gmail.com");
    customer.setAddress("int test, 000");
    customer.setPassword("Int-Test1");
    customerRepository.save(customer);

    Order orderEntity = OrderConvert.toEntity(customer);
    orderEntity.setIdTransaction("order-test");
    orderRepository.save(orderEntity);

    List<OrderResponse> orderResponse =
            OrderConvert.toResponseList(List.of(orderEntity));

    Mockito.when(FindOrder.findAll()).thenReturn(orderResponse);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].idTransaction")
                    .value("order-test")
    );
  }

  @Test
  void orderGetIdWithSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("customer-int2");
    customer.setName("int-test2");
    customer.setEmail("int-test2@gmail.com");
    customer.setAddress("int test, 000");
    customer.setPassword("Int-Test1");
    customerRepository.save(customer);

    Order orderEntity = OrderConvert.toEntity(customer);
    orderEntity.setIdTransaction("order-test2");
    orderRepository.save(orderEntity);

    OrderResponse orderResponse =
            OrderConvert.toResponseOrder(orderEntity);

    Mockito.when(FindOrder.findById("order-test2")).thenReturn(orderResponse);

    mockMvc.perform(
            MockMvcRequestBuilders.get("/order/order-test2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.idTransaction")
                    .value("order-test2")
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.customer")
                    .value("customer-int2")
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.status")
                    .value("OPEN")
    );
  }
}
