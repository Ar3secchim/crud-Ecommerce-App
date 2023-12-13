package com.crud.modules.integration.order.controller;

import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import com.crud.modules.order.entity.Order;
import com.crud.modules.order.repository.OrderRepository;
import com.crud.modules.orderItem.DTO.OrderItemRequest;
import com.crud.modules.orderItem.entity.OrderItem;
import com.crud.modules.orderItem.repository.OrdemItemRepository;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.utils.OrdemItemConvert;
import com.crud.utils.OrderConvert;
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
@AutoConfigureMockMvc
public class OrderUpdateControllerIntegrationTest {
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrdemItemRepository ordemItemRepository;

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;

  @Test
  void updateOrderWithSuccess() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("test");
    customer.setName("test");
    customer.setEmail("test@gmail.com");
    customer.setAddress("int-test, 000");
    customer.setPassword("Int-test");
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
    OrderItem orderItem = OrdemItemConvert.toEntity(orderItemRequest,
            orderEntity, product);
    orderItem.setIdTransaction("orderItemUpdate");
    ordemItemRepository.save(orderItem);

    OrderItemRequest orderItemRequestUpdate = new OrderItemRequest();
    orderItemRequestUpdate.setProductId("product-id");
    orderItemRequestUpdate.setAmount(4);

    String orderItemUpdate =
            mapper.writeValueAsString(orderItemRequestUpdate);

    mockMvc.perform(
            MockMvcRequestBuilders.put("/order/ordemItem/" + orderItem.getIdTransaction())
                    .content(orderItemUpdate)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.orderId").isNotEmpty()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.amount").value(4)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.total").value(1000.00)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.orderId").value("order-test")
    );
  }

  @Test
  void updateOrderWithAmountZero() throws Exception {
    Customer customer = new Customer();
    customer.setIdTransaction("test2");
    customer.setName("test2");
    customer.setEmail("test2@gmail.com");
    customer.setAddress("int-test, 000");
    customer.setPassword("Int-test2");
    customerRepository.save(customer);

    Order orderEntity = OrderConvert.toEntity(customer);
    orderEntity.setIdTransaction("order-test2");
    orderRepository.save(orderEntity);

    Product product = new Product();
    product.setSkuId("product-id2");
    product.setName("product2");
    product.setPrice(BigDecimal.valueOf(250));
    product.setQuantityStock(10);
    product.setDescription("product test");
    productRepository.save(product);

    OrderItemRequest orderItemRequest = new OrderItemRequest();
    orderItemRequest.setProductId("product-id2");
    orderItemRequest.setAmount(2);

    OrderItem orderItem = OrdemItemConvert.toEntity(orderItemRequest,
            orderEntity, product);
    orderItem.setIdTransaction("orderItemUpdate2");
    ordemItemRepository.save(orderItem);

    OrderItemRequest orderItemRequestUpdate = new OrderItemRequest();
    orderItemRequestUpdate.setProductId("product-id2");
    orderItemRequestUpdate.setAmount(0);

    String orderItemUpdate =
            mapper.writeValueAsString(orderItemRequestUpdate);

    mockMvc.perform(
            MockMvcRequestBuilders.put("/order/ordemItem/" + orderItem.getIdTransaction())
                    .content(orderItemUpdate)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.orderId").isNotEmpty()
    );
  }
}
