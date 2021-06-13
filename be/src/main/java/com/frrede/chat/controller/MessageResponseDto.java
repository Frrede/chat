package com.frrede.chat.controller;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDto {
  private final UUID id;
  private final String token;
  private final String name;
  private final String message;
  private final LocalDateTime date;
}
