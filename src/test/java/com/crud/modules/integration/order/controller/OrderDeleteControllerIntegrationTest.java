package com.crud.modules.integration.order.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.order.usecase.DeleteOrder;
import com.crud.utils.OrderConvert;
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

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderDeleteControllerIntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  OrderRepository orderRepository;

  @MockBean
  private DeleteOrder deleteOrder;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void OrderDeleteWithSuccess() throws Exception {
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

    mockMvc.perform(
            MockMvcRequestBuilders.delete("/order/" + orderEntity.getIdTransaction())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isNoContent()
    );

    verify(deleteOrder, times(1)).execute(orderEntity.getIdTransaction());
  }

  @Test
  void OrderDeleteWithNotFound() throws Exception {
    doThrow(new BadRequestClient("Order not found"))
            .when(deleteOrder).execute("unit");

    mockMvc.perform(
            MockMvcRequestBuilders.delete("/order/unit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is4xxClientError()
    ).andExpect(MockMvcResultMatchers.jsonPath("$.errors")
            .value("Order not found"));
  }
}
