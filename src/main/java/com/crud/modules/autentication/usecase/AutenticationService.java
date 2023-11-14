package com.crud.modules.autentication.usecase;

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
  public Customer loadUserByUsername(String email) throws UsernameNotFoundException {
    return customerRepository.findByEmail(email);
  }
}
