package com.crud.modules.integration.product.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.UpdateProduct;
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

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UpdateProductControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  ProductRepository repository;

  @MockBean
  UpdateProduct updateProduct;

  @Test
  void updateProductWithSuccess() throws Exception {
    Product product = new Product();
    product.setName("product");
    product.setPrice(BigDecimal.valueOf(100));
    product.setSkuId("productSku");
    product.setQuantityStock(5);
    product.setDescription("product");
    repository.save(product);

    ProductRequest productRequest = new ProductRequest();
    productRequest.setName("product int");
    productRequest.setQuantityStock(50);
    productRequest.setDescription("product-int");

    String productUpdate = mapper.writeValueAsString(productRequest);

    mockMvc.perform(
            MockMvcRequestBuilders.put("/product/" + product.getSkuId())
                  .content(productUpdate)
                  .contentType(MediaType.APPLICATION_JSON)
                  .accept(MediaType.APPLICATION_JSON)
            ).andDo(
                    MockMvcResultHandlers.print()
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
    );
  }

  @Test
  void updateProductWithInvalid() throws Exception {
    ProductRequest productRequest = new ProductRequest();
    String productUpdate = mapper.writeValueAsString(productRequest);

    doThrow(new BadRequestClient("Product not found "))
            .when(updateProduct).execute(any(),any());

    mockMvc.perform(
            MockMvcRequestBuilders.put("/product/unit")
                    .content(productUpdate)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(
                    MockMvcResultHandlers.print()
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.errors").value("Product not found ")
            );
  }
}
