package com.crud.modules.customers.usecase;

import com.crud.modules.customers.DTO.CustomerResponse;

import java.util.List;

public interface IListCustomerUseCase {
  List<CustomerResponse> listAll();

}
