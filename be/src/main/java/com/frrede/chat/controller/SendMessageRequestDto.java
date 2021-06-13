package com.frrede.chat.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageRequestDto {
  private final String name;
  private final String message;
}
