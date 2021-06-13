package com.frrede.chat.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutgoingMessage {
  private final UUID id;
  private final String token;
  private final String name;
  private final String message;
  private final LocalDateTime date;
}
