package com.vichen.messageQueue;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vichen
 */
@Service public class ActiveMqProducer {
  @Resource JmsMessagingTemplate jmsMessagingTemplate;

  public void send(String dest, String message) {
    jmsMessagingTemplate.convertAndSend(dest, message);

  }
}
