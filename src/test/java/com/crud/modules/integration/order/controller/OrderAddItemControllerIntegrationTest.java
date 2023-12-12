package com.crud.modules.integration.order.controller;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.OrderConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OrderAddItemControllerIntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void addItemOrderSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("int-test");
    customer.setName("int-test");
    customer.setEmail("int-test@gmail.com");
    customer.setAddress("int-test, 000");
    customer.setPassword("Int-test1");
    customerRepository.save(customer);

    Order orderEntity = OrderConvert.toEntity(customer);
    orderEntity.setIdTransaction("order-test");
    orderRepository.save(orderEntity);

    Product product = new Product();
    product.setSkuId("product-id");
    product.setName("product");
    product.setPrice(BigDecimal.valueOf(250));
    product.setQuantityStock(10);
    product.setDescription("product test");
    productRepository.save(product);

    OrderItemRequest orderItemRequest = new OrderItemRequest();
    orderItemRequest.setProductId("product-id");
    orderItemRequest.setAmount(2);

    String orderItem = mapper.writeValueAsString(orderItemRequest);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/order/order-test")
                    .content(orderItem)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isCreated()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.orderId").isNotEmpty()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.amount").value(2)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.total").value(500.00)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.orderId").value("order-test")
    );
  }
}
