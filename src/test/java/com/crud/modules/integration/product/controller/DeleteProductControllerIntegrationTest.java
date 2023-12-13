package com.crud.modules.integration.product.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.product.entity.Product;
import com.crud.modules.product.repository.ProductRepository;
import com.crud.modules.product.usecase.DeleteProduct;
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

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DeleteProductControllerIntegrationTest {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DeleteProduct deleteProduct;
  @Test
  public void deleteProductWithSuccess() throws Exception {
    Product product = new Product();
    product.setSkuId("int-product");
    product.setName("int-product");
    product.setPrice(BigDecimal.valueOf(250));
    product.setQuantityStock(10);
    product.setDescription("product test");
    productRepository.save(product);

    mockMvc.perform(
            MockMvcRequestBuilders.delete("/product/int-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isNoContent()
    );

    verify(deleteProduct, times(1)).execute(product.getSkuId());
  }

  @Test
  public void GetProductByIdNull() throws Exception {
    doThrow(new BadRequestClient("Product not found "))
            .when(deleteProduct).execute(any());

    mockMvc.perform(
            MockMvcRequestBuilders.delete("/product/int")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
    ).andDo(
            MockMvcResultHandlers.print()
    ).andExpect(
            MockMvcResultMatchers.status().isBadRequest()
    );
  }
}
