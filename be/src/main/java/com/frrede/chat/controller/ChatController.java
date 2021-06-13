package com.frrede.chat.controller;

import com.frrede.chat.domain.ChatService;
import com.frrede.chat.domain.IncomingMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

  private final ChatService chatService;

  @PostMapping()
  @CrossOrigin
  public void sendMessage(@RequestBody() SendMessageRequestDto sendMessageRequestDto, @RequestHeader() String token) {
    chatService.createMessage(IncomingMessage.builder()
        .token(token)
        .name(sendMessageRequestDto.getName())
        .message(sendMessageRequestDto.getMessage())
        .build()
    );
  }

  @GetMapping()
  @CrossOrigin
  public GetAllMessagesResponseDto getAllMessages(
      @RequestParam()
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      Optional<LocalDateTime> startingDate
  ) {
    var outgoingMessages = startingDate.isPresent() ?
        chatService.getAllMessagesGreaterThan(startingDate.get()) :
        chatService.getAllMessages();

    var messages = outgoingMessages
        .stream()
        .map(outgoingMessage -> MessageResponseDto.builder()
            .id(outgoingMessage.getId())
            .token(outgoingMessage.getToken())
            .name(outgoingMessage.getName())
            .message(outgoingMessage.getMessage())
            .date(outgoingMessage.getDate())
            .build())
        .collect(Collectors.toList());

    return GetAllMessagesResponseDto.builder().messages(messages).build();
  }
}
