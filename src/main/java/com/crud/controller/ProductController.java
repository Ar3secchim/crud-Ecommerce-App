package com.crud.controller;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;
import com.crud.service.ProductService;
import com.crud.utils.ProductConvert;
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

  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){
    ProductResponse productResponse =  productService.create(ProductConvert.toEntity(productRequest));
    return ResponseEntity
            .created(URI.create("/product/"+productResponse.getId()))
            .body(productResponse);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getProduct(){
    return ResponseEntity.ok(productService.listAll());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProductById (@PathVariable  Integer productId){
    ProductResponse product = productService.findById(productId);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponse>  deleteProduct(@PathVariable Integer id){
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
