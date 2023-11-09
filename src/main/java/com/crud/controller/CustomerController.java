package com.crud.controller;

import com.crud.controller.dto.customer.CustomerRequest;
import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.controller.dto.product.ProductResponse;
import com.crud.controller.exception.PasswordValidationError;
import com.crud.controller.exception.ValidationError;
import com.crud.service.CustomerService;
import com.crud.utils.CustomerConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @Operation(summary = "Create a customer", description = "Returns a customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible create customer")
  })
  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(
          @Valid @RequestBody CustomerRequest customerDTO
  ) throws PasswordValidationError, ValidationError {
    CustomerResponse customerResponse = customerService.create(CustomerConvert.toEntity(customerDTO));
    return ResponseEntity.created(
              URI.create("/customer/"+customerResponse.getId()))
            .body(customerResponse);
  }

  @Operation(summary = "Get all customer", description = "Returns a list customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible get all customers")
  })
  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
    return ResponseEntity.ok(customerService.listAll());
  }

  @Operation(summary = "Get customer by email", description = "Returns customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible get customers")
  })
  @GetMapping("email/{email}")
  public ResponseEntity<CustomerResponse> getCustomerByEmail( @PathVariable String email){
    return ResponseEntity.ok(customerService.findByEmail(email));
  }

  @Operation(summary = "Get customer by id", description = "Return customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible get customers")
  })
  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id){
    return ResponseEntity.ok(customerService.findById(id));
  }

  @Operation(summary = "Get customer by name", description = "Return customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "404", description = "Not possible get customer")
  })
  @GetMapping("/name/{name}")
  public ResponseEntity<List<CustomerResponse>> getCustomerByName(@PathVariable String name){
    return ResponseEntity.ok(customerService.findByName(name));
  }

  @Operation(summary = "Update customer by id", description = "Return customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "404", description = "Not possible update customer")
  })
  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Integer id,
                                                         @RequestBody CustomerRequest customerRequest){
    return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
  }

  @Operation(summary = "Delete a customer by id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "404", description = "Not found - The customer was not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<ProductResponse> deleteCustomer(@PathVariable Integer id){
    customerService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
