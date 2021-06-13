package com.frrede.chat.persistance;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, UUID> {
  List<Message> findByOrderByDateAsc();

  List<Message> findByDateGreaterThanOrderByDateAsc(Date date);
}
