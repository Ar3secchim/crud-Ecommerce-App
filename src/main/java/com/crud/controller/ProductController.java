package com.crud.controller;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.service.ProductService;
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
  ProductService productService;

  @Operation(summary = "Create a product", description = "Returns a product")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "400", description = "Not possible create product")
  })
  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){
    ProductResponse productResponse =  productService.create(ProductConvert.toEntity(productRequest));
    return ResponseEntity
            .created(URI.create("/product/"+productResponse.getId()))
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
    return ResponseEntity.ok(productService.listAll());
  }

  @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
  })
  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProductById (@PathVariable  Integer productId){
    ProductResponse product = productService.findById(productId);
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
                                                       @PathVariable Integer productId){
    return ResponseEntity.ok(productService.updateProduct(productId, productRequest));
  }

  @Operation(summary = "Delete a product by id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "403", description = "Forbidden"),
          @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponse>  deleteProduct(@PathVariable Integer id){
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
