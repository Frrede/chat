package com.frrede.chat.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ChatService {
  public void createMessage(IncomingMessage message) {
    System.out.println(message);
  }

  public List<OutgoingMessage> getAllMessages() {
    return List.of(OutgoingMessage.builder().build());
  }

  public List<OutgoingMessage> getAllMessagesAfterStartingDate(LocalDateTime startingDate) {
    return List.of(OutgoingMessage.builder().build());
  }
}
