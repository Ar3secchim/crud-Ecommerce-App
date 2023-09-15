package integration.email;

import java.util.List;

public class SendEmail {
  public void send(String from, List<String> to, String content) {
    System.out.println("Enviando email de: " + from + ", para: " + to);
    System.out.println("Conteúdo: " + content);
  }
}
