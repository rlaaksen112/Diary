package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
