package com.crud.service;

import com.crud.controller.dto.customer.CustomerResponse;
import com.crud.model.Customer;
import com.crud.model.QCustomer;
import com.crud.usecases.ICustomerUseCase;
import com.crud.repository.CustomerRepository;
import com.crud.utils.CustomerConvert;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerUseCase {
  @Autowired
  CustomerRepository repository;

  public CustomerResponse create(Customer customer) {
    return CustomerConvert.toResponse(repository.save(customer));
  }

  public List<CustomerResponse> listAll() {
    return CustomerConvert.toListResponse(repository.findAll());
  }

  public CustomerResponse findByEmail(String email) {
    QCustomer qCustomer = QCustomer.customer;
    BooleanExpression booleanExpression =  qCustomer.email.eq(email);

    return CustomerConvert.toResponse(repository.findOne(booleanExpression).get());
  }

  public CustomerResponse findById(Integer id) {
    Customer found = null;

    if (id != null) {
      found = repository.findCustomerById(id);
    }

    return CustomerConvert.toResponse(found);
  }

  public List<CustomerResponse> findByName(String name) {
    List<Customer> found = new ArrayList<>();

    if (name != null) {
      found = repository.findByName(name);
    }

    return CustomerConvert.toListResponse(found);
  }

  public void delete(Integer id){
    if (id != null) {
      repository.deleteById(id);
    }
  }
}
