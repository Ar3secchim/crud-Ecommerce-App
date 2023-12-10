package com.crud.modules.customers.controller;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerRequestUpdate;
import com.crud.modules.customers.DTO.CustomerResponse;
import com.crud.modules.customers.usecase.*;
import com.crud.utils.CustomerConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  @Autowired
  RegisterCustomer registerCustomer;
  @Autowired
  FindCustomer findCustomer;
  @Autowired
  ListCustomer listCustomer;
  @Autowired
  UpdateCustomer updateCustomer;
  @Autowired
  DeleteCustomer deleteCustomer;

  @Operation(summary = "Create a customer", description = "Returns a customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible create customer")
  })
  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(
          @Valid @RequestBody CustomerRequest customerRequest
  ) throws Exception {
    CustomerResponse customerResponse = registerCustomer.execute(CustomerConvert.toEntity(customerRequest));
    return ResponseEntity.created(
              URI.create("/customer/"+ customerResponse.getIdTransaction()))
            .body(customerResponse);
  }

  @Operation(summary = "Get all customer", description = "Returns a list customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible get all customers")
  })
  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
    return ResponseEntity.ok(listCustomer.execute());
  }

  @Operation(summary = "Get customer by email", description = "Returns customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible get customers")
  })
  @GetMapping("email/{email}")
  public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable String email) throws BadRequestClient {
    return ResponseEntity.ok(findCustomer.findByEmail(email));
  }

  @Operation(summary = "Get customer by id", description = "Return customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "400", description = "Not possible get customers")
  })
  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) throws BadRequestClient {
    return ResponseEntity.ok(findCustomer.findById(id));
  }

  @Operation(summary = "Get customer by name", description = "Return customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "404", description = "Not possible get customer")
  })
  @GetMapping("/name/{name}")
  public ResponseEntity<List<CustomerResponse>> getCustomerByName(@PathVariable String name) throws BadRequestClient {
    return ResponseEntity.ok(findCustomer.findByName(name));
  }

  @Operation(summary = "Update customer by id", description = "Return customer")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "404", description = "Not possible update customer")
  })
  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id,
                                                         @RequestBody CustomerRequestUpdate customerRequest) throws Exception {
    return ResponseEntity.ok(updateCustomer.execute(id, customerRequest));
  }

  @Operation(summary = "Delete a customer by id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
          @ApiResponse(responseCode = "404", description = "Not found - The customer was not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable String id) throws Exception {
    deleteCustomer.execute(id);
    return ResponseEntity.noContent().build();
  }
}
