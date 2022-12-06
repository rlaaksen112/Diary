package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByMember(Member member);


}
