package com.frrede.chat.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frrede.chat.domain.ChatService;
import com.frrede.chat.domain.IncomingMessage;
import com.frrede.chat.domain.OutgoingMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ChatService chatService;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Nested
  @DisplayName("#sendMessage()")
  class Send {
    ResultActions resultActions;

    @BeforeEach
    void before() throws Exception {
      resultActions = mockMvc.perform(
          post("/chat")
              .header("token", "123")
              .content(objectMapper.writeValueAsString(
                  SendMessageRequestDto.builder()
                      .name("Testy McTest")
                      .message("Just a test")
                      .build()
              ))
              .contentType(MediaType.APPLICATION_JSON)
      );
    }

    @Test
    void whenSendMessage_createIt() throws Exception {
      resultActions
          .andExpect(status().isOk());
    }

    @Test
    void whenSendMessage_callServiceToCreateIt() {
      verify(chatService).createMessage(IncomingMessage.builder()
          .token("123")
          .name("Testy McTest")
          .message("Just a test")
          .build()
      );
    }
  }

  @Nested
  @DisplayName("#getAllMessages() without startingTime")
  class GetAllWithoutStartingTime {
    ResultActions resultActions;

    @BeforeEach
    void before() throws Exception {
      when(chatService.getAllMessages()).thenReturn(List.of(
          OutgoingMessage.builder()
              .id(UUID.fromString("7880c221-aabc-4695-ab75-76fa026ece29"))
              .token("TOKEN")
              .name("Testy McTest")
              .message("Just a test")
              .date(LocalDateTime.parse("2021-01-01T10:11:30"))
              .build()
      ));

      resultActions = mockMvc.perform(get("/chat"));
    }

    @Test
    void whenGetAllMessagesWithoutStartingTime_returnMessages() throws Exception {
      resultActions
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages.size()").value(1))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].id").value("7880c221-aabc-4695-ab75-76fa026ece29"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].token").value("TOKEN"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].name").value("Testy McTest"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].message").value("Just a test"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].date").value("2021-01-01T10:11:30"));
    }
  }

  @Nested
  @DisplayName("#getAllMessages() with startingTime")
  class GetAllWithStartingTime {
    ResultActions resultActions;

    @BeforeEach
    void before() throws Exception {
      when(chatService.getAllMessagesGreaterThan(any())).thenReturn(List.of(
          OutgoingMessage.builder()
              .id(UUID.fromString("7880c221-aabc-4695-ab75-76fa026ece29"))
              .token("TOKEN")
              .name("Testy McTest")
              .message("Just a test")
              .date(LocalDateTime.parse("2021-01-01T10:11:30"))
              .build()
      ));

      resultActions = mockMvc.perform(get("/chat").param("startingDate", "2021-01-01T10:11:29"));
    }

    @Test
    void whenGetAllMessagesWithStartingTime_returnMessages() throws Exception {
      resultActions
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages.size()").value(1))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].id").value("7880c221-aabc-4695-ab75-76fa026ece29"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].token").value("TOKEN"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].name").value("Testy McTest"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].message").value("Just a test"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.messages[0].date").value("2021-01-01T10:11:30"));
    }
  }
}