package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;

import java.util.List;

public interface IFindCustomerUseCase {
  CustomerResponse findByEmail(String email);

  CustomerResponse findById(String id);

  List<CustomerResponse> findByName(String name);
}
