package com.crud.ecommerce.app.application.integration.sms;

import com.crud.ecommerce.app.application.INotifierUserCase;
import com.crud.ecommerce.app.domain.Customer;

public class CustomerSmsNotifierImpl implements INotifierUserCase<Customer> {

  private SendSms sendSms;

  public CustomerSmsNotifierImpl(SendSms sendSms) {
    this.sendSms = sendSms;
  }

  @Override
  public void registered(Customer customer) {
    sendSms.send("1111111111111", customer.getTelephone(), "Bem vindo. Click no link abaixo para confirmar seu cadastro.");
  }

  @Override
  public void updated(Customer customer) {
    sendSms.send("1111111111111", customer.getTelephone(), "Suas informações foram atualizadas. Caso tenha sido você mesmo, pode ignorar esse email.");
  }

  @Override
  public void deleted(Customer customer) {
    sendSms.send("1111111111111", customer.getTelephone(), "Sentiremos sua falta e esperamos seu retorno logo.");
  }

}
