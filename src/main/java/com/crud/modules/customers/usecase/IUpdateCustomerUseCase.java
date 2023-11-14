package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerRequest;
import com.crud.modules.customers.DTO.CustomerResponse;

public interface IUpdateCustomerUseCase {
  CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest);
}
