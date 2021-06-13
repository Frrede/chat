package com.frrede.chat.controller;

import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

  @PostMapping()
  public void sendMessage(@RequestBody() SendMessageRequestDto sendMessageRequestDto, @RequestHeader() String token) {
    System.out.println(sendMessageRequestDto);
    System.out.println(token);
  }

  @GetMapping()
  public GetAllMessagesResponseDto getAllMessages(
      @RequestParam()
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      Optional<Date> startingDate
  ) {
    System.out.println(startingDate);
    return GetAllMessagesResponseDto.builder().build();
  }
}
