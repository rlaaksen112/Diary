package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Member;
import com.spring.min.diary.Model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TextRepository extends JpaRepository<Text,Integer> {
    Optional<Text> findByMember(Member member);
}
