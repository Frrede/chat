package com.frrede.chat.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomingMessage {
  private final String token;
  private final String name;
  private final String message;
}
