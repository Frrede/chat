package com.frrede.chat.controller;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDto {
  private final String token;
  private final String name;
  private final String message;
  private final LocalDateTime date;
}
