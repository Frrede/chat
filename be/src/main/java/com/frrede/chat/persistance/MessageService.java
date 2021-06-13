package com.frrede.chat.persistance;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class MessageService {
  private final MessageRepository messageRepository;

  public void createMessage(Message message) {
    messageRepository.save(message);
  }

  public List<Message> getAllMessages() {
    return messageRepository.findByOrderByDateAsc();
  }

  public List<Message> getAllMessagesGreaterThan(Date date) {
    return messageRepository.findByDateGreaterThanOrderByDateAsc(date);
  }
}
