package com.crud.ecommerce.app.application.integration.sms;

import java.util.List;

public class SendSms {
  public void send(String from, List<String> to, String content) {
    System.out.println("Enviando sms de: " + from + ", para: " + to);
    System.out.println("Conteúdo: " + content);
  }

}
