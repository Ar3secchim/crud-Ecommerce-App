package com.crud.modules.product.controller;

import com.crud.modules.product.DTO.ProductRequest;
import com.crud.modules.product.DTO.ProductResponse;
import com.crud.modules.product.usecase.CreateProduct;
import com.crud.modules.product.usecase.DeleteProduct;
import com.crud.modules.product.usecase.FindProduct;
import com.crud.modules.product.usecase.UpdateProduct;
import com.crud.utils.ProductConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  CreateProduct createProductService;
  @Autowired
  FindProduct findProductService;
  @Autowired
  UpdateProduct updateProductService;
  @Autowired
  DeleteProduct deleteProductService;

  @Operation(summary = "Create a product", description = "Returns a product")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "400", description = "Not possible create product")
  })
  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) throws Exception {
    ProductResponse productResponse =  createProductService.execute(productRequest);
    return ResponseEntity
            .created(URI.create("/product/"+productResponse.getSku()))
            .body(productResponse);
  }

  @Operation(summary = "Get all product", description = "Returns a list products")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
  })
  @GetMapping
  public ResponseEntity<List<ProductResponse>> getProduct(){
    return ResponseEntity.ok(findProductService.listAll());
  }

  @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
  })

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProductById (@PathVariable  String productId){
    ProductResponse product = findProductService.findById(productId);
    return ResponseEntity.ok(product);
  }

  @Operation(summary = "Update a product", description = "Returns a product update")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
  })
  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,
                                                       @PathVariable String productId) throws Exception {
    return ResponseEntity.ok(updateProductService.execute(productId, productRequest));
  }

  @Operation(summary = "Delete a product by id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String id) throws Exception {
    deleteProductService.execute(id);
    return ResponseEntity.noContent().build();
  }
}
