package com.spring.min.diary.Repository;

import com.spring.min.diary.Model.Board;
import com.spring.min.diary.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board , Integer> {
    List<Board> findByMemberLike(Member member);

    Optional<Board> findByTitleAndMember(String title,Member member);

}
