package com.crud.modules.autentication.usecase;

import com.crud.infra.exception.BadRequestClient;
import com.crud.modules.customers.entity.Customer;
import com.crud.modules.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService {
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Customer loadUserByUsername(String email) {
   Customer customer = customerRepository.findByEmail(email);
   if (customer == null) try {
     throw new BadRequestClient("Customer not found");
   } catch (BadRequestClient e) {
     throw new RuntimeException(e.getMessage());
   }
   return customer;
  }
}
