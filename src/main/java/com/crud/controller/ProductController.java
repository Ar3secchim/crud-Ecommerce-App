package com.crud.controller;

import com.crud.controller.dto.product.ProductRequest;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.model.Product;
import com.crud.service.ProductService;
import com.crud.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  ProductService productService;

  @PostMapping
  public ProductResponse createCustomer(@RequestBody ProductRequest productRequest){
    Product product =  productService.create(ProductConvert.toEntity(productRequest));
    return ProductConvert.toResponse(product);
  }

  @GetMapping
  public List<ProductResponse> getProduct(){
    return productService.listAll();
  }

  @GetMapping("/id")
  public ProductResponse getProduct(
          @RequestParam(name = "productBarcode", required = false) String productBarcode,
          @RequestParam(name = "productId", required = false) Integer productId
  ){
    return productService.getAllProduct(productBarcode, productId);
  }

  @DeleteMapping("/{id}")
  public ProductResponse deleteProductById(@PathVariable Integer id){
    return productService.deleteProductById(id);
  }

}
