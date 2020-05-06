package com.vichen.messageQueue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author vichen
 */
@Service public class ActiveMqConsumer {
  @JmsListener(destination = "SpringBootTest") public void subscribe(String text) {
    System.out.println("ActiveMqConsumer onMessage: " + text);
  }
}
