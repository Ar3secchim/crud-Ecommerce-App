package com.crud.modules.integration.product.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.FindProduct;
import com.crud.utils.ProductConvert;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductGetControllerIntegrationTest {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FindProduct findProduct;

  @Test
  public void GetAllProduct() throws Exception {
    Product product = new Product();
    product.setSkuId("int-product");
    product.setName("int-product");
    product.setPrice(BigDecimal.valueOf(250));
    product.setQuantityStock(10);
    product.setDescription("product test");
    productRepository.save(product);

    List<ProductResponse> productResponse =
            ProductConvert.toListResponse(List.of(product));

    Mockito.when(findProduct.listAll()).thenReturn(productResponse);

    mockMvc.perform(
            MockMvcRequestBuilders.get("/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].name")
                    .value("int-product")
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].quantityStock")
                    .value(10)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$[0].price")
                    .value(250.00)
    );
  }

  @Test
  public void GetProductById() throws Exception {
    Product product = new Product();
    product.setSkuId("int-product");
    product.setName("int-product");
    product.setPrice(BigDecimal.valueOf(250));
    product.setQuantityStock(10);
    product.setDescription("product test");
    productRepository.save(product);

    ProductResponse productResponse =
            ProductConvert.toResponse(product);

    Mockito.when(findProduct.findById("int-product")).thenReturn(productResponse);

    mockMvc.perform(
            MockMvcRequestBuilders.get("/product/int-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().is2xxSuccessful()
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.name")
                    .value("int-product")
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.quantityStock")
                    .value(10)
    ).andExpect(
            MockMvcResultMatchers.jsonPath("$.price")
                    .value(250.00)
    );
  }

  @Test
  public void GetProductByIdNull() throws Exception {
    when(findProduct.findById("int"))
            .thenThrow(new BadRequestClient("Product not found with ID: int-product"));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/product/int")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isBadRequest()
    );
  }
}
