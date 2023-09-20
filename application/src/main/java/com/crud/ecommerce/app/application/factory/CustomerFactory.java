package com.crud.ecommerce.app.application.factory;

import com.crud.ecommerce.app.application.ICustomerUseCase;
import com.crud.ecommerce.app.application.INotifierUserCase;
import com.crud.ecommerce.app.application.impl.CustomerUseCaseImpl;
import com.crud.ecommerce.app.application.integration.database.MemoryDatabase;
import com.crud.ecommerce.app.application.integration.email.CustomerEmailNotifierImpl;
import com.crud.ecommerce.app.application.integration.email.SendEmail;
import com.crud.ecommerce.app.application.integration.memoryrepository.CustomerMemoryRepositoryImpl;
import com.crud.ecommerce.app.application.repository.CustomerRepository;
import com.crud.ecommerce.app.domain.Customer;

public class CustomerFactory {

  public static ICustomerUseCase createUseCase() {
    return new CustomerUseCaseImpl(
            createRepository(),
            createNotifier()
    );
  }


  public static CustomerRepository createRepository() {
    return new CustomerMemoryRepositoryImpl(MemoryDatabase.getInstance());
  }

  public static INotifierUserCase<Customer> createNotifier() {
    return new CustomerEmailNotifierImpl(new SendEmail());
  }
}
