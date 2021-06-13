package com.frrede.chat.domain;

import com.frrede.chat.persistance.Message;
import com.frrede.chat.persistance.MessageService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatService {

  private final MessageService messageService;

  public void createMessage(IncomingMessage message) {
    messageService.createMessage(Message.builder()
        .token(message.getToken())
        .name(message.getName())
        .message(message.getMessage())
        .date(new Date())
        .build()
    );
  }

  public List<OutgoingMessage> getAllMessages() {
    return messageService.getAllMessages()
        .stream()
        .map(message -> OutgoingMessage
            .builder()
            .id(message.getId())
            .token(message.getToken())
            .name(message.getName())
            .message(message.getMessage())
            .date(convertToLocalDateTime(message.getDate()))
            .build())
        .collect(Collectors.toList());
  }

  public List<OutgoingMessage> getAllMessagesGreaterThan(LocalDateTime date) {
    return messageService.getAllMessagesGreaterThan(Timestamp.valueOf(date))
        .stream()
        .map(message -> OutgoingMessage
            .builder()
            .id(message.getId())
            .token(message.getToken())
            .name(message.getName())
            .message(message.getMessage())
            .date(convertToLocalDateTime(message.getDate()))
            .build())
        .collect(Collectors.toList());
  }

  private LocalDateTime convertToLocalDateTime(Date date) {
    return new Timestamp(date.getTime()).toLocalDateTime();
  }
}
