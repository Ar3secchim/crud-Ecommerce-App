package factory;

import integration.database.MemoryDatabase;
import integration.email.CustomerEmailNotifierImpl;
import integration.email.SendEmail;
import integration.memoryrepository.CustomerMemoryRepositoryImpl;
import model.Customer;
import usecases.ICustomerUseCase;
import usecases.INotifierUserCase;
import usecases.impl.CustomerUseCaseImpl;
import usecases.repository.CustomerRepository;

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
