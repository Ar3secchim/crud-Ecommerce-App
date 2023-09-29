package com.crud.usecases;

import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;

import java.util.List;

public interface ICustomerUseCase {
  CustomerResponse create(Customer customer);
  List<CustomerResponse> listAll();
  CustomerResponse findByEmail(String email);
  CustomerResponse findById(Integer id);
  List<CustomerResponse> findByName(String name);
}
