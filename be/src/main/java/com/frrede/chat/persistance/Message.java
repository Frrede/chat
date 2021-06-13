package com.frrede.chat.persistance;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="message")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;

  @Column(nullable = false)
  String token;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  String message;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  Date date;
}
