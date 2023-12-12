package com.crud.modules.integration.product.controller;

import com.crud.modules.product.DTO.ProductRequest;
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
public class ProductCreateControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void createProductWithSuccess() throws Exception {
    ProductRequest productRequest = new ProductRequest();
    productRequest.setName("unit-product");
    productRequest.setPrice(BigDecimal.valueOf(100));
    productRequest.setSkuId("unit-product-sku");
    productRequest.setQuantityStock(10);
    productRequest.setDescription("product");

    String product = mapper.writeValueAsString(productRequest);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/product")
                    .content(product)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isCreated()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.skuId").isNotEmpty()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name").value("unit-product")
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.quantityStock").value(10)
    );
  }

}
