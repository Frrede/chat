package com.frrede.chat.controller;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllMessagesResponseDto {
  private final List<MessageResponseDto> messages;
}
